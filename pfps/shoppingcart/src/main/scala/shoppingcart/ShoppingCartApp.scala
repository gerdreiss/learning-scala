package shoppingcart

import cats.effect._
import cats.implicits._

object ShoppingCartApp extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = IO {
    println("Shopping cart starting up...")
  } >> ExitCode.Success.pure[IO]

}

