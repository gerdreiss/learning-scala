package cats

object ValidationV2 extends App {

  import cats.instances.list._ // Semigroup for List
  import cats.syntax.either._
  import cats.syntax.semigroup._ // |+| syntax

  sealed trait Check[E, A] {
    def and(that: Check[E, A]): Check[E, A] = And(this, that)
    def apply(a: A)(implicit s: Semigroup[E]): Either[E, A] =
      this match {
        case Pure(func) => func(a)
        case And(left, right) =>
          (left(a), right(a)) match {
            case (Left(e1), Left(e2))  => (e1 |+| e2).asLeft
            case (Left(e),  Right(_))  => e.asLeft
            case (Right(_), Left(e))   => e.asLeft
            case (Right(_), Right(_))  => a.asRight
          }
      }
  }

  final case class And[E, A](left: Check[E, A], right: Check[E, A]) extends Check[E, A]
  final case class Pure[E, A](func: A => Either[E, A]) extends Check[E, A]

  val a: Check[List[String], Int] =
    Pure { v =>
      if(v > 2) v.asRight
      else List("Must be > 2").asLeft
    }

  val b: Check[List[String], Int] =
    Pure { v =>
      if(v < -2) v.asRight
      else List("Must be < -2").asLeft
    }

  val check = a and b

  println(check(5))
  println(check(2))
}
