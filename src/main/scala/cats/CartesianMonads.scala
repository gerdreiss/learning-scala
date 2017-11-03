package cats

import scala.language.higherKinds

object CartesianMonads extends App {

  import cats.instances.list._
  import cats.instances.option._
  import cats.syntax.flatMap._
  import cats.syntax.functor._


  def product[M[_] : Monad, A, B](fa: M[A], fb: M[B]): M[(A, B)] =
    for {
      a <- fa
      b <- fb
    } yield (a, b)

  println(product(Option(1), Option(2)))
  println(product(List(1, 2), List(3, 4)))

}
