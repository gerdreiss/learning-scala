package fp.red.chapter3

object Lists {

  sealed trait List[+A]

  case object Nil extends List[Nothing]

  case class Cons[+A](head: A, tail: List[A]) extends List[A] {
    override def toString: String =
      head + ", " + tail.toString
  }

  object List {
    def sum(ints: List[Int]): Int =
      ints match {
        case Nil => 0
        case Cons(x, xs) => x + sum(xs)
      }

    def product(ds: List[Double]): Double =
      ds match {
        case Nil => 1.0
        case Cons(0.0, _) => 0.0
        case Cons(x, xs) => x * product(xs)
      }

    def apply[A](as: A*): List[A] =
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

    def tail[A](as: List[A]): List[A] =
      as match {
        case Nil => sys.error("tail of empty list")
        case Cons(_, t) => t
      }

    def setHead[A](h: A, as: List[A]): List[A] =
      as match {
        case Nil => sys.error("setHead of empty list")
        case Cons(_, t) => Cons(h, t)
      }

    def drop[A](as: List[A], n: Int): List[A] =
      as match {
        case Nil => sys.error("drop elements of empty list")
        case Cons(h, t) if n > 0 => drop(t, n - 1)
        case _ => as
      }

    def dropWhile[A](as: List[A], p: A => Boolean): List[A] =
      as match {
        case Nil => as
        case Cons(h, t) if p(h) => dropWhile(t, p)
        case _ => as
      }

    def incr(ints: List[Int]): List[Int] = ints match {
      case Nil => Nil
      case Cons(h, t) => Cons(h + 1, incr(t))
    }

    def map[A, B](as: List[A])(f: A => B): List[B] =
      as match {
        case Nil => Nil
        case Cons(h, t) => Cons(f(h), map(t)(f))
      }
  }

  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5)
    println(list)
    println(List.incr(list))
    println(List.tail(list))
    println(List.drop(list, 2))
    println(List.map(list)(_.toString + "_"))
    println(List.dropWhile(list, (x: Int) => x < 3))
    val x = list match {
      case Cons(x, Cons(2, Cons(4, _))) => x
      case Nil => 42
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
      case Cons(h, t) => h + List.sum(t)
      case _ => 101
    }
    println(x)
  }
}
