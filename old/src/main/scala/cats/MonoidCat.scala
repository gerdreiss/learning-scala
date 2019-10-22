package cats

object MonoidCat extends App {

  import cats.instances.all._
  import cats.syntax.semigroup._

  println(Option(1) |+| Option(2))

  val map1 = Map("a" -> 1, "b" -> 2)
  val map2 = Map("c" -> 3, "d" -> 4)

  println(map1 |+| map2)

  val tuple1 = ("hello", 123)
  val tuple2 = ("world", 456)

  println(tuple1 |+| tuple2 |+| cats.Monoid[(String, Int)].empty)
}
