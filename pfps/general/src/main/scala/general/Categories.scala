package general

import cats._
import cats.implicits._

import scala.util.Random
import scala.util.control.NoStackTrace

sealed trait BusinessError extends NoStackTrace
case object RandomError extends BusinessError

sealed trait Category

trait Categories[F[_]] {
  def findAll: F[List[Category]]
  def maybeFindAll: F[Either[RandomError.type, List[Category]]]
}

class LiveCategories[F[_]: MonadError[*[_], Throwable]: Random] extends Categories[F] {
  override def findAll: F[List[Category]] = Random[F].bool.ifM(
    List.empty[Category].pure[F],
    RandomError.raiseError[F, List[Category]]
  )
}

