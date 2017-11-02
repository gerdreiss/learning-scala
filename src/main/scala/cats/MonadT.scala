package cats

import cats.data.{EitherT, OptionT}
import cats.instances.future._
import cats.syntax.applicative._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object MonadT extends App {

  type FutureEither[A] = EitherT[Future, String, A]
  type FutureEitherOption[A] = OptionT[FutureEither, A]

  val answer: FutureEitherOption[Any] =
    for {
      a <- 100.pure[FutureEitherOption]
      b <- 320.pure[FutureEitherOption]
      e <- "E".pure[FutureEitherOption]
    } yield a + b + e

  Await.result(answer.value.value, Duration.Inf)

  println(answer)
}
