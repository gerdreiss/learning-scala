package cats

object Timestamps extends App {

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent._
  import scala.concurrent.duration._

  lazy val timestamp0 = System.currentTimeMillis

  def getTimestamp: Long = {
    val timestamp = System.currentTimeMillis - timestamp0
    Thread.sleep(100)
    timestamp
  }

  val timestamps = for {
    a <- Future(getTimestamp)
    b <- Future(getTimestamp)
    c <- Future(getTimestamp)
  } yield (a, b, c)

  val result = Await.result(timestamps, 1.second)

  println()
  println(result)
}
