package codecentric

object AdHocPolymorphism {

  trait Semigroup[A] {
    def add(x: A, y: A): A
  }

  object Semigroup {
    def apply[A: Semigroup]: Semigroup[A] = implicitly
  }

  trait Monoid[A] extends Semigroup[A] {
    def zero: A
  }

  object Monoid {
    def apply[A: Monoid]: Monoid[A] = implicitly
  }

  implicit object Integers extends Monoid[Int] {
    override val zero = 0

    override def add(x: Int, y: Int): Int = x + y
  }

  implicit object Strings extends Monoid[String] {
    override val zero = ""

    override def add(x: String, y: String): String = x + y
  }

  def combine[A: Semigroup](x: A, y: A): A =
    Semigroup[A].add(x, y)

  def aggregate[A: Monoid](xs: Iterable[A]): A =
    xs.fold(Monoid[A].zero)(Monoid[A].add)

  def main(args: Array[String]): Unit = {
    println(aggregate(Seq(1, 2, 3)))
    println(aggregate(Seq("abc", "xyz")))
  }
}