package general

import cats._
import cats.data._
import org.http4s._
import org.http4s.dsl._

import scala.util.control.NoStackTrace

sealed trait UserError extends NoStackTrace
final case class UserAlreadyExists(username: String) extends UserError
final case class UserNotFound(username: String) extends UserError
final case class InvalidUserAge(age: Int) extends UserError

trait HttpErrorHandler[F[_], E <: Throwable] {
  def handle(routes: HttpRoutes[F]): HttpRoutes[F]
}

abstract class RoutesHttpErrorHandler[F[_], E <: Throwable] extends HttpErrorHandler[F, E] with Http4sDsl[F] {

  def A: ApplicativeError[F, E]

  def handler: E => F[Response[F]]

  def handle(routes: HttpRoutes[F]): HttpRoutes[F] =
    Kleisli { req =>
      OptionT {
        A.handleErrorWith(routes.run(req).value)(e => A.map(handler(e))(Option(_)))
      }
    }

}

//object ClassyPrisms {
//  val users: Users[F] = ???
//
//  val httpRoutes: HttpRoutes[F] =
//    HttpRoutes.of {
//      case GET -> Root =>
//        Ok(users.findAll)
//    }
//
//  def routes(
//      ev: HttpErrorHandler[F, UserError]
//  ): HttpRoutes[F] =
//    ev.handle(httpRoutes)
//
//}
