package shoppingcart

import cats.effect.concurrent.Ref
import cats.implicits._
import cats.effect._

object Algebra {

  trait Counter[F[_]] {
    def incr: F[Unit]
    def get: F[Int]
  }

  class LiveCounter[F[_]] private (
      ref: Ref[F, Int]
  ) extends Counter[F] {
    def incr: F[Unit] = ref.update(_ + 1)
    def get: F[Int]   = ref.get
  }

  object LiveCounter {
    def make[F[_]: Sync]: F[Counter[F]] =
      Ref.of[F, Int](0).map(new LiveCounter(_))
  }
}
