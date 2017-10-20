package fp.lfpis

import java.lang.Thread.sleep

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object ForFutures extends App {

  // (a) create the futures
  val f1 = Future { sleep(10 * 1000); 1 }
  val f2 = Future { sleep( 2 * 1000); 2 }
  val f3 = Future { sleep( 4 * 1000); 3 }

  // (b) run them simultaneously in a for-comprehension
  val result: Future[Int] = for {
    r1 <- f1
    r2 <- f2
    r3 <- f3
  } yield r1 + r2 + r3

  // (c) do whatever you need to do with the result
  result.onComplete {
    case Success(x) => println(s"\nresult = $x")
    case Failure(e) => e.printStackTrace()
  }
}
