package fp.tothemax

import scala.util.Try
import scala.util.Random
import scala.io.StdIn.readLine

/**
  * Programming a guessing game with John de Goes:
  * https://www.youtube.com/watch?v=sxudIMiOo68
  */
object Game extends App {
  def parseInt(s: String): Option[Int] = Try(s.toInt).toOption

  case class IO[A](unsafeRun: () => A) { self =>
    def map[B](f: A => B): IO[B] = IO(() => f(self.unsafeRun()))
    def flatMap[B](f: A => IO[B]): IO[B] = f(self.unsafeRun())
  }
  object IO {
    def point[A](a: => A): IO[A] = IO(() => a)
  }

  def putStrLn(line: String): IO[Unit] = IO(() => println(line))
  def getStrLn: IO[String] = IO(() => readLine())

  def nextInt(upper: Int): IO[Int] = IO(() => Random.nextInt(upper))

  def checkContinue(name: String): IO[Boolean] = for {
    _     <- putStrLn(s"Do you want to continue, $name?")
    input <- getStrLn.map(_.toLowerCase)
    cont  <- input match {
      case "y" => IO.point(true)
      case "n" => IO.point(false)
      case _ => checkContinue(name)
    }
  } yield cont

  def gameLoop(name: String): IO[Unit] = for {
    num   <- nextInt(5).map(_ + 1)
    _     <- putStrLn(s"Dear $name, please guess a number from 1 to 5: ")
    input <- getStrLn
    _     <- parseInt(input)
               .fold(putStrLn("You did not enter a number")) { guess =>
                 if (guess == num) putStrLn(s"You guessed right, $name!")
                 else              putStrLn(s"You guessed wrong, $name! The number was: $num")
               }
    cont  <- checkContinue(name)
    _     <- if (cont) gameLoop(name) else IO.point(())
  } yield ()

  def main: IO[Unit] = for {
    _    <- putStrLn("What is your name? ")
    name <- getStrLn
    _    <- putStrLn(s"Hello, $name, welcome to the game!")
    _    <- gameLoop(name)
  } yield ()

  main.unsafeRun
}
