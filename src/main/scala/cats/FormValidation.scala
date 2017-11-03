package cats

import cats.data.Validated._
import cats.data._
import cats.syntax.all._

// TODO does not work!!!

object FormValidation extends App {

  type FormData = Map[String, String]
  type Errors = List[String]
  type ErrorsOr[A] = Either[Errors, A]
  type AllErrorsOr[A] = Validated[Errors, A]

  case class User(name: String, age: Int)

  def printIt[A](result: ErrorsOr[A]): Unit =
    result match {
      case Right(s) => println(s"found '$s'")
      case Left(es) => println(es.mkString("\n"))
    }

  def printItAll[A](result: AllErrorsOr[A]): Unit =
    result match {
      case Valid(a) => println(a)
      case Invalid(es) => println(es.mkString("\n"))
    }

  def getValue(name: String)(data: FormData): ErrorsOr[String] =
    data.get(name)
      .toRight(List(s"'$name' field not specified"))

  def parseInt(name: String)(data: String): ErrorsOr[Int] =
    Right(data)
      .flatMap(s => Either.catchOnly[NumberFormatException](s.toInt))
      .leftMap(_ => List(s"'$name' must be an integer"))

  def nonBlank(name: String)(data: String): ErrorsOr[String] =
    Right(data)
      .ensure[Errors](List(s"'$name' cannot be blank"))(_.nonEmpty)

  def nonNegative(name: String)(data: Int): ErrorsOr[Int] =
    Right(data)
      .ensure[Errors](List(s"'$name' must be non-negative"))(_ >= 0)

  def readName(data: FormData): ErrorsOr[String] =
    getValue("name")(data).
      flatMap(nonBlank("name"))

  def readAge(data: FormData): ErrorsOr[Int] =
    getValue("age")(data).
      flatMap(nonBlank("age")).
      flatMap(parseInt("age")).
      flatMap(nonNegative("age"))

  def readUser(data: FormData): AllErrorsOr[User] =
    Cartesian.map2(
      readName(data).toValidated,
      readAge(data).toValidated
    )(User.apply)


  printIt(getValue("name")(Map("name" -> "Dade Murphy")))
  printIt(getValue("name")(Map()))
  printIt(parseInt("age")("11"))
  printIt(parseInt("age")("11a"))
  printIt(nonBlank("name")("Dade Murphy"))
  printIt(nonBlank("name")(""))
  printIt(nonNegative("age")(11))
  printIt(nonNegative("age")(-1))
  printIt(readName(Map("name" -> "Dade Murphy")))
  printIt(readName(Map("name" -> "")))
  printIt(readName(Map()))
  printIt(readAge(Map("age" -> "11")))
  printIt(readAge(Map("age" -> "-1")))
  printIt(readAge(Map()))
  printItAll(readUser(Map("name" -> "Dade Murphy", "age" -> "11")))
  printItAll(readUser(Map("name" -> "Dade Murphy")))
  printItAll(readUser(Map("age" -> "11")))
  printItAll(readUser(Map()))


}
