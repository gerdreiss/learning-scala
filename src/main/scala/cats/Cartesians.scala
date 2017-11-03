package cats

object Cartesians extends App {

  import cats.implicits._

  case class Cat(name: String, color: String)

  println(Cartesian.tuple3(1.some, 2.some, 3.some))
  println(Cartesian.map3(1.some, 2.some, 3.some)(_ + _ + _))
  println(Cartesian.map2("Garfield".some, "red".some)(Cat.apply))

}
