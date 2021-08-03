import scala.concurrent.ExecutionContext.Implicits.*
import scala.concurrent.Future
import scala.runtime.stdLibPatches.language.future
import scala.util.Random
import scala.util.control.NonFatal

def getPagesCount(): Future[Int] = Future(42)

def getPage(page: Int): Future[String] =
  if Random.nextDouble > .95 then Future.failed(Exception(s"Timeout when fetchin page $page"))
  else Future(s"Page $page")

def getAllPages(): Future[Seq[String]] =
  getPagesCount().flatMap { pagesCount =>
    Future.traverse(1 to pagesCount)(getPage)
  }

def resilientGetPage(page: Int): Future[String] =
  val maxAttempts = 3

  def attempt(remainingAttempts: Int): Future[String] =
    if remainingAttempts == 0 then
      println(s"Failed to fetch $page after $maxAttempts attempts")
      Future.failed(Exception(s"Failed after $maxAttempts"))
    else
      println(s"Trying to fetch $page ($remainingAttempts remaining attempts)")
      getPage(page)
        .recoverWith { case NonFatal(_) =>
          System.err.println(s"Fetching page $page failed...")
          attempt(remainingAttempts - 1)
        }

  attempt(maxAttempts)

def resilientGetAllPages(): Future[Seq[String]] =
  getPagesCount().flatMap { pagesCount =>
    (1 to pagesCount)
      .foldLeft[Future[Seq[String]]](Future.successful(Vector.empty)) { (evtlPrevPages, pageNum) =>
        evtlPrevPages.flatMap { prevPages =>
          resilientGetPage(pageNum)
            .map(pageContent => prevPages :+ pageContent)
        }
      }
  }

@main def runFuturePages() =
  resilientGetAllPages()
    .map(_.mkString("\n"))
    .onComplete(println)
