package cats

object ShowCat extends App {

  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.show._

  println(1.show)
  println("1".show)

}
