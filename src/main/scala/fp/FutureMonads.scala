package fp

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.language.higherKinds

object FutureMonads extends App {

  trait Monad[F[_]] {
    /** Constructor (said to lift a value `A` in the `F[A]`
     * monadic context). Also part of `Applicative`, see below.
     */
    def pure[A](a: A): F[A]

    /** FTW */
    def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

    /** implement map via flatMap */
    def map[A, B](fa: F[A])(f: A => B): F[B] =
      flatMap(fa)(a => pure(f(a)))
  }

  // Supplying an instance for Future isn't clean, ExecutionContext needed
  class FutureMonad(implicit ec: ExecutionContext) extends Monad[Future] {

    def pure[A](a: A): Future[A] =
      Future.successful(a)

    def flatMap[A, B](fa: Future[A])(f: A => Future[B]): Future[B] =
      fa.flatMap(f)
  }

  object FutureMonad {
    implicit def instance(implicit ec: ExecutionContext): FutureMonad =
      new FutureMonad
  }

  /** Calculates the N-th number in a Fibonacci series. */
  def fib[F[_]](n: Int)(implicit F: Monad[F]): F[BigInt] = {
    def loop(n: Int, a: BigInt, b: BigInt): F[BigInt] =
      F.flatMap(F.pure(n)) { n =>
        if (n <= 1) F.pure(b)
        else loop(n - 1, b, a + b)
      }

    loop(n, BigInt(0), BigInt(1))
  }

  // Needed in scope
  import FutureMonad.instance
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.duration.DurationInt

  // this alone doesn't print jack
  fib[Future](40).map(r => println(s"Result: $r"))

  // with this, both expressions (above and below) are evaluated. WTF???
  Await.result(
    fib[Future](40).map(r => println(s"Result: $r")),
    1.second
  )
  //=> Result: 102334155
}
