package cats

object Validations extends App {

  import cats.syntax.validated._

  println(123.valid[String])
}
