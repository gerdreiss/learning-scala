package exercism

object Bob {

  val lookup: Seq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') :+ '?'

  val yelling  = "^[A-Z0-9]*[A-Z][A-Z0-9]*"
  val question = "\\?$"

  val YQ = (yelling + question).r
  val Y  = (yelling + "$").r
  val Q  = (".*"    + question).r

  def response(statement: String): String =
    statement filter lookup.contains match {
      case YQ() => "Calm down, I know what I'm doing!"
      case Y()  => "Whoa, chill out!"
      case Q()  => "Sure."
      case ""   => "Fine. Be that way!"
      case _    => "Whatever."
    }
}
