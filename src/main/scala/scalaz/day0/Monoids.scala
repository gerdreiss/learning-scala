package scalaz.day0

import scala.language.{higherKinds, implicitConversions}

object Monoids extends App {

  trait Monoid[A] {
    def mappend(a1: A, a2: A): A
    def mzero: A
  }

  object Monoid {
    implicit val IntMonoid: Monoid[Int] = new Monoid[Int] {
      def mappend(a: Int, b: Int): Int = a + b
      def mzero: Int = 0
    }
    implicit val StringMonoid: Monoid[String] = new Monoid[String] {
      def mappend(a: String, b: String): String = a + b
      def mzero: String = ""
    }
  }

  trait FoldLeft[F[_]] {
    def foldLeft[A, B](xs: F[A], z: B, f: (B, A) => B): B
  }

  object FoldLeft {
    implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
      def foldLeft[A, B](xs: List[A], z: B, f: (B, A) => B): B = xs.foldLeft(z)(f)
    }
  }

  // Method injection
  trait MonoidOp[A] {
    val F: Monoid[A]
    val value: A

    def |+|(a2: A): A = F.mappend(value, a2)
  }

  implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
    val F: Monoid[A] = implicitly[Monoid[A]]
    val value: A = a
  }


  // Variant one where the Monoid is passed as parameter
  //def sum[A](xs: List[A], m: Monoid[A]): A = xs.foldLeft(m.zero)(m.op)

  // Variant two where the Monoid is passed implicitly as parameter
  //def sum[A](xs: List[A])(implicit m: Monoid[A]): A = xs.foldLeft(m.zero)(m.op)

  // Variant three where the Monoid is implicitly used without declaring it as parameter
  def sum[A: Monoid](xs: List[A]): A = {
    val m = implicitly[Monoid[A]]
    xs.foldLeft(m.mzero)(m.mappend)
  }

  def sum[M[_] : FoldLeft, A: Monoid](xs: M[A]): A = {
    val m = implicitly[Monoid[A]]
    val fl = implicitly[FoldLeft[M]]
    fl.foldLeft(xs, m.mzero, m.mappend)
  }

  def plus[A: Monoid](a: A, b: A): A = implicitly[Monoid[A]].mappend(a, b)

  private val ints = List(1, 2, 3, 4, 5)
  private val strings = List("a", "b", "c")

  println(sum(ints))
  println(sum(strings))

  // We can still provide different monoid directly to the function.
  // We could provide an instance of monoid for Int using multiplications

  val explMultipl: Monoid[Int] = new Monoid[Int] {
    def mappend(a: Int, b: Int): Int = a * b
    def mzero: Int = 1
  }

  println(sum(ints)(explMultipl))
  println(sum(ints))

  // These results come from MonoidOp
  println(3 |+| 4)
  println("a" |+| "b")

}
