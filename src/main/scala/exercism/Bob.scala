package exercism

import scala.util.matching.Regex

object Bob {

  val lookup: Seq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') :+ '?'
  val yelling = "^[A-Z0-9]*[A-Z][A-Z0-9]*"
  val question = "\\?$"
  val any = ".*"
  val YQ: Regex = (yelling + question).r
  val Y: Regex = (yelling + "$").r
  val Q: Regex = (any + question).r

  def response(statement: String): String =
    statement filter lookup.contains match {
      case YQ() => "Calm down, I know what I'm doing!"
      case Y()  => "Whoa, chill out!"
      case Q()  => "Sure."
      case ""   => "Fine. Be that way!"
      case _    => "Whatever."
    }
}
