import cats.effect.IOApp
import cats.effect.IO

object Main extends IOApp.Simple {

  override def run: IO[Unit] =
    IO.consoleForIO.println("Hello PFPS2")

}
