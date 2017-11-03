package cats

object Hmmm extends App {

  import cats.syntax.either._

  def parseInt(s: String): Either[String, Int] =
    Either.catchOnly[NumberFormatException](s.toInt)
      .leftMap(e => s"Could not read '$s' coz of $e")


  val result = for {
    a <- parseInt("a")
    b <- parseInt("b")
    c <- parseInt("c")
  } yield a + b + c

  result match {
    case Left(s) => println(s)
    case Right(i) => println(s"read $i")
  }
}
