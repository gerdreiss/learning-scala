package fp

import java.util.UUID

import cats.free.Free
import cats.implicits._
import cats.{Monad, ~>}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.higherKinds

object FreeMonadVsTaglessFinal {

  case class User(id: UUID, email: String, loyaltyPoints: Int)

  object UsingFuture {

    trait UserRepository {
      def findUser(id: UUID): Future[Option[User]]
      def updateUser(u: User): Future[Unit]
    }

    class LoyaltyPoints(users: UserRepository) {
      def addPoints(userId: UUID, points: Int): Future[Either[String, Unit]] =
        users.findUser(userId).flatMap {
          case None =>
            Future.successful(Left("User not found."))
          case Some(user) =>
            users.updateUser(user.copy(loyaltyPoints = user.loyaltyPoints + points)).map(_ => Right(()))
        }
    }
  }

  object UsingFreeMonad {

    sealed trait UserRepositoryADT[A]
    case class FindUser(id: UUID) extends UserRepositoryADT[Option[User]]
    case class UpdateUser(u: User) extends UserRepositoryADT[Unit]

    type UserRepository[A] = Free[UserRepositoryADT, A]

    def findUser(id: UUID): UserRepository[Option[User]] = Free.liftF(FindUser(id))
    def updateUser(u: User): UserRepository[Unit] = Free.liftF(UpdateUser(u))

    def addPoints(userId: UUID, points: Int): UserRepository[Either[String, Unit]] =
      findUser(userId) flatMap {
        case None =>
          Free.pure(Left("User not found"))
        case Some(user) =>
          updateUser(user.copy(loyaltyPoints = user.loyaltyPoints + points)).map(_ => Right(()))
      }

    val futureInterpreter: UserRepositoryADT ~> Future = new (UserRepositoryADT ~> Future) {
      override def apply[A](fa: UserRepositoryADT[A]): Future[A] = fa match {
        case FindUser(_) =>
          Future.successful(Some(User(UUID.fromString("1"), "my@mail.com", 10)))
        case UpdateUser(_) =>
          Future.successful(())
      }
    }

    val result: Future[Either[String, Unit]] =
      addPoints(UUID.randomUUID(), 10)
        .foldMap(futureInterpreter)
  }

  object UsingTaglessFinal {

    trait UserRepositoryADT[F[_]] {
      def findUser(id: UUID): F[Option[User]]
      def updateUser(u: User): F[Unit]
    }

    class LoyaltyPoints[F[_] : Monad](users: UserRepositoryADT[F]) {
      def addPoints(userId: UUID, points: Int): F[Either[String, Unit]] =
        users.findUser(userId) flatMap {
          case None =>
            implicitly[Monad[F]].pure(Left("User not found."))
          case Some(user) =>
            users.updateUser(user.copy(loyaltyPoints = user.loyaltyPoints + points)).map(_ => Right(()))
        }
    }

    object FutureInterpreter extends UserRepositoryADT[Future] {
      override def findUser(id: UUID): Future[Option[User]] =
        Future.successful(Some(User(UUID.fromString("1"), "my@mail.com", 10)))
      override def updateUser(u: User): Future[Unit] =
        Future.successful(())
    }

    val result: Future[Either[String, Unit]] =
      new LoyaltyPoints(FutureInterpreter)
        .addPoints(UUID.randomUUID(), 10)

  }

}
