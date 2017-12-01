package cats

object Cartesians extends App {

  import cats.implicits._

  case class Cat(name: String, color: String)

  println(Semigroupal.tuple3(1.some, 2.some, 3.some))
  println(Semigroupal.map3(1.some, 2.some, 3.some)(_ + _ + _))
  println(Semigroupal.map2("Garfield".some, "red".some)(Cat.apply))

}
