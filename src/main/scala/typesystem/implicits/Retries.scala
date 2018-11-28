package typesystem.implicits

import scala.util.Random
import scala.util.control.NonFatal

object Retries extends App {

  case class RetryParams(times: Int)

  def retryCall[A](fn: => A, currentTry: Int = 0)(retryParams: RetryParams): A = {
    try fn
    catch {
      case NonFatal(_) if currentTry < retryParams.times =>
        retryCall(fn, currentTry + 1)(retryParams)
    }
  }

  def retry[A](fn: => A)(implicit retryParams: RetryParams): A =
    retryCall(fn)(retryParams)



  val max = Random.nextInt(16)

  implicit val retries: RetryParams = RetryParams(max)

  def log[A](max: A)(fn: => A): A = {
    val probe = fn
    println(s"Checking $probe == $max ...")
    probe
  }

  retry {

    val probe = log(max) {
      Random.nextInt(max + 1)
    }

    require(probe == max, s"Probe never reached $max")

    probe

  }

  println(s"Probe reached $max")

}
