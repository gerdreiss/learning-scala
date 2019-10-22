package cats

object ShowCat extends App {

  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.show._

  println(1.show)
  println("1".show)

  import java.util.Date

  //implicit val dateShow: Show[Date] =
  //  new Show[Date] {
  //    def show(date: Date): String =
  //      s"${date.getTime}ms since the epoch."
  //  }

  implicit val dateShow: Show[Date] =
    Show.show(date => s"${date.getTime} ms since the epoch.")

  println(new Date())
  println(new Date().show)

}
