package shoppingcart

import cats._
import cats.effect._
import cats.implicits._
import io.chrisdavenport.log4cats._
import retry.RetryDetails.{GivingUp, WillDelayAndRetry}
import retry.RetryPolicies._
import retry._

import scala.concurrent.duration._

//object Programs {
//
//  import Algebras._
//  import Domain._
//
//  final class CheckoutProgram[F[_] : Monad : MonadError](
//    paymentClient: PaymentClient[F],
//    shoppingCart : ShoppingCart[F],
//    orders       : Orders[F]
//  ) {
//
//    val retryPolicy: RetryPolicy[F] =
//      limitRetries[F](3) |+| exponentialBackoff[F](10.milliseconds)
//
//    def checkout(userId: UserId, card: Card): F[OrderId] =
//      for {
//        cart <- shoppingCart.get(userId)
//        paymentId <- paymentClient.process(userId, cart.total, card)
//        orderId <- orders.create(userId, paymentId, cart.items, cart.total)
//        _ <- shoppingCart.delete(userId).attempt.void
//      } yield orderId
//
//    def logError(action: String)(
//      e: Throwable,
//      details: RetryDetails
//    ): F[Unit] =
//      details match {
//        case r: WillDelayAndRetry =>
//          Logger[F].error(
//            s"Failed on $action. We retried ${r.retriesSoFar} times."
//          )
//        case g: GivingUp =>
//          Logger[F].error(
//            s"Giving up on $action after ${g.totalRetries} retries."
//          )
//      }
//
//    def processPayment(
//      userId: UserId,
//      total: USD,
//      card  : Card
//    ): F[PaymentId] = {
//      val action = retryingOnAllErrors[PaymentId](
//        policy = retryPolicy,
//        onError = logError("Payments")
//      )(paymentClient.process(userId, total, card))
//
//      action.adaptError {
//        case e => PaymentError(e.getMessage)
//      }
//    }
//
//    def createOrder(
//      userId: UserId,
//      paymentId: PaymentId,
//      items: List[CartItem],
//      total: USD
//    ): F[OrderId] = {
//      val action = retryingOnAllErrors[OrderId](
//        policy = retryPolicy,
//        onError = logError("Order")
//      )(orders.create(userId, paymentId, items, total))
//
//      action
//        .adaptError {
//          case e => OrderError(e.getMessage)
//        }
//        .onError {
//          “ case _ =>
//            Logger[F].error(
//              s"Failed to create order for: ${paymentId}"
//            ) *>
//              Background[F].schedule(action, 1.hour)
//        }
//    }
//
//
//    def retry[A](fa: F[A]): F[A] =
//      Timer[F].sleep(50.milliseconds) >> fa
//  }
//
//}
