package cats

import cats.data.Writer

object LoggerT extends App {

  type Logged[A] = Writer[List[String], A]

  def parseNumber(str: String): Logged[Option[Int]] =
    util.Try(str.toInt).toOption match {
      case Some(num) => Writer(List(s"Read $str"), Some(num))
      case None => Writer(List(s"Failed on $str"), None)
    }

  def addNumbers(a: String, b: String, c: String): Logged[Option[Int]] = {
    import cats.data.OptionT
    import cats.instances.list._

    val result = for {
      a <- OptionT(parseNumber(a))
      b <- OptionT(parseNumber(b))
      c <- OptionT(parseNumber(c))
    } yield a + b + c

    result.value
  }


  val result1 = addNumbers("1", "2", "3")
  val result2 = addNumbers("1", "a", "3")

  println(result1)
  println(result2)

}
