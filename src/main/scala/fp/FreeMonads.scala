package fp

import cats.free.Free
import cats.free.Free._
import cats.implicits._
import cats.{Id, ~>}

object FreeMonads extends App {

  type Symbol = String
  type Response = String

  sealed trait Orders[A]
  case class Buy(stock: Symbol, amount: Int) extends Orders[Response]
  case class Sell(stock: Symbol, amount: Int) extends Orders[Response]

  type OrdersF[A] = Free[Orders, A]

  def buy(stock: Symbol, amount: Int): OrdersF[Response] = liftF[Orders, Response](Buy(stock, amount))
  def sell(stock: Symbol, amount: Int): OrdersF[Response] = liftF[Orders, Response](Sell(stock, amount))

  val flatMapThat: OrdersF[Response] =
    buy("APPL", 50)
      .flatMap(_ => buy("MSFT", 10))
      .flatMap(_ => sell("GOOG", 200))

  val smartTrade: OrdersF[Response] = for {
    _ <- buy("APPL", 50)
    _ <- buy("MSFT", 10)
    rsp <- sell("GOOG", 200)
  } yield rsp

  // interpreter
  def orderPrinter: Orders ~> Id =
    new (Orders ~> Id) {
      def apply[A](fa: Orders[A]): Id[A] = fa match {
        case Buy(stock, amount) =>
          println(s"Buying $amount of $stock")
          "ok"
        case Sell(stock, amount) =>
          println(s"Selling $amount of $stock")
          "ok"
      }
    }

  type ErrorOr[A] = Either[String, A]

  // xor interpreter
  def xorInterpreter: Orders ~> ErrorOr =
    new (Orders ~> ErrorOr) {
      def apply[A](fa: Orders[A]): ErrorOr[A] =
        fa match {
          case Buy(stock, amount) =>
            s"$stock - $amount".asRight[String]
          case Sell(_, _) =>
            "Why are you selling that?".asLeft
        }
    }

  smartTrade.foldMap(orderPrinter)
  println(smartTrade.foldMap(xorInterpreter))
}
