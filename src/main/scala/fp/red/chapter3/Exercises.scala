package fp.red.chapter3

object Exercises {

  sealed trait List[+A]

  case object Nil extends List[Nothing]

  case class Cons[+A](head: A, tail: List[A]) extends List[A] {
    override def toString: String =
      head + ", " + tail.toString
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def length[A](as: List[A]): Int = foldRight(as, 0)((_, y) => y + 1)

  def main(args: Array[String]): Unit = {
    println(length(Cons(1, Cons(2, Cons(3, Cons(4, Nil))))))
  }
}
