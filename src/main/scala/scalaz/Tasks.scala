package scalaz

import scala.concurrent.Future
import scalaz.concurrent.Task

object Tasks extends App {

  def performAction(num: Int): Unit =
    println(s"Task #$num is executing in ${Thread.currentThread().getName}")

  import scala.concurrent.ExecutionContext.Implicits.global

  val resultF = Future {
    performAction(0)
  }

  val result2F = Future.successful {
    performAction(1)
  }

  val result2T = Task.now {
    performAction(2)
  }

  val result3T = Task {
    performAction(3)
  }

  val result4T = Task.delay {
    performAction(4)
  }

}
