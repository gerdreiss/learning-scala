package steps

object Step5 {

  trait Numeric[A] {
    def add(l: A, r: A): A
  }

  object Numeric {
    def apply[A](implicit n: Numeric[A]): Numeric[A] = n
  }

  implicit class NumericSyntax[A](l: A) {
    def +(r: A)(implicit n: Numeric[A]): A = n.add(l, r)
  }

}
