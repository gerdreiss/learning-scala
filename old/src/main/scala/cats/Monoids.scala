package cats


object Monoids extends App {

  import cats.implicits.catsKernelStdMonoidForString
  import DefaultMonoids._
  import cats.instances.int.catsKernelStdGroupForInt
  import cats.instances.map.catsKernelStdMonoidForMap

  trait Monoid[A] {
    def compose(a: A, b: A): A
    def empty: A
  }

  object DefaultMonoids {
    implicit val stringConcatMonoid = new Monoid[String] {
      override def compose(a: String, b: String): String = s"$a$b"
      override def empty: String = ""
    }
  }

  object Operations {
    def combineAll[A](list: List[A])(implicit monoid: Monoid[A]): A = {
      list.foldRight(monoid.empty)((a, b) => monoid.compose(a, b))
    }
  }


  val result = Operations.combineAll(List("a", "b", "c"))
  println(result)

  val result2 = cats.kernel.Monoid[String].combineAll(List("c", "a", "ts"))
  println(result2)


  val scores = List(Map("Joe" -> 12, "Kate" -> 21), Map("Joe" -> 10))
  val totals = cats.kernel.Monoid[Map[String, Int]].combineAll(scores)
  println(totals)

}

