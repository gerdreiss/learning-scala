package neophytesguide

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationDouble
import scala.concurrent.{Await, Future, Promise}
import scala.language.postfixOps
import scala.util.{Failure, Success}

case class TaxCut(reduction: Int)

object Government extends App {
  def redeemCampaignPledge(): Future[TaxCut] = {
    val p = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period.")
      Thread.sleep(2000)
      p.success(TaxCut(20))
      println("We reduced the taxes! You must reelect us!!!!1111")
    }
    p.future
  }

  println("Let's see if they remember their promises...")

  val taxCutF: Future[TaxCut] = Government.redeemCampaignPledge()

  taxCutF.onComplete {
    case Success(TaxCut(reduction)) =>
      println(s"Yay! They cut our taxes by $reduction percentage points!")
    case Failure(exeption) =>
      println(s"They broke their promises! Because of a $exeption")
  }

  // 3 - this is blocking (blocking is bad)
  val result = Await.result(taxCutF, 5 second)

  // An operation must be called on the future result to actually invoke the onComplete part!
  println(result)
}

