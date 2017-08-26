package scalaz

import scalaz._
import Scalaz._

object Disjunctions extends App {

  class GenerationException(number: Long, message: String) extends Exception(message)

  def queryNextNumberEither: Exception \/ Long = {
    val source = Math.round(Math.random() * 100)
    if (source > 60) \/.left(new Exception("The generated number is too big!"))
    else \/.right(source)
  }

  def queryNextNumberTry: Throwable \/ Long = \/.fromTryCatchNonFatal {
    val source = Math.round(Math.random() * 100)
    if (source > 60) throw new Exception("The generated number is too big!")
    source
  }

  // only necessary if fromTryCatchThrowable not type parametrized
  // implicit val geNotNothing = NotNothing.isNotNothing[GenerationException]

  def queryNextNumberNotNothing: GenerationException \/ Long = \/.fromTryCatchThrowable[Long, GenerationException] {
    val source = Math.round(Math.random() * 100)
    if (source > 60) throw new GenerationException(source, "The generated number is too big!")
    source
  }

  val lst = List(queryNextNumberNotNothing, queryNextNumberNotNothing, queryNextNumberNotNothing)
  val lstD = lst.sequenceU

  println(queryNextNumberEither)
  println(queryNextNumberTry)
  println(queryNextNumberNotNothing)
  println(lstD)

}
