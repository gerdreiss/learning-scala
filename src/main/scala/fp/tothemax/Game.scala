package fp.tothemax

import scala.util.Try
import scala.io.StdIn.readLine
import scala.language.higherKinds

/**
  * Programming a guessing game with John de Goes:
  * https://www.youtube.com/watch?v=sxudIMiOo68
  */
object Game extends App {
  def parseInt(s: String): Option[Int] = Try(s.toInt).toOption

  // PROGRAM
  trait Program[F[_]] {
    def finish[A](a: => A): F[A]
    def chain[A, B](fa: F[A], aft: A => F[B]): F[B]
    def map[A, B](fa: F[A], ab: A => B): F[B]
  }
  object Program {
    def apply[F[_]](implicit F: Program[F]): Program[F] = F
  }
  implicit class ProgramSyntax[F[_], A](fa: F[A]) {
    def map[B](f: A => B)(implicit F: Program[F]): F[B] = F.map(fa, f)
    def flatMap[B](afb: A => F[B])(implicit F: Program[F]): F[B] = F.chain(fa, afb)
  }
  def finish[F[_], A](a: => A)(implicit F: Program[F]): F[A] = F.finish(a)

  // CONSOLE
  trait Console[F[_]] {
    def putStrLn(line: String): F[Unit]
    def getStrLn: F[String]
  }
  object Console {
    def apply[F[_]](implicit F: Console[F]): Console[F] = F
  }
  def putStrLn[F[_]: Console](line: String): F[Unit] = Console[F].putStrLn(line)
  def getStrLn[F[_]: Console]: F[String] = Console[F].getStrLn

  // RANDOM
  trait Random[F[_]] {
    def nextInt(upper: Int): F[Int]
  }
  object Random {
    def apply[F[_]](implicit F: Random[F]): Random[F] = F
  }
  def nextInt[F[_]: Random](upper: Int): F[Int] = Random[F].nextInt(upper)

  // IO
  case class IO[A](unsafeRun: () => A) { self =>
    def map[B](f: A => B): IO[B] = IO(() => f(self.unsafeRun()))
    def flatMap[B](f: A => IO[B]): IO[B] = f(self.unsafeRun())
  }
  object IO {
    def point[A](a: => A): IO[A] = IO(() => a)

    implicit val ProgramIO = new Program[IO] {
      def finish[A](a: => A): IO[A] = IO.point(a)
      def chain[A, B](fa: IO[A], afb: A => IO[B]): IO[B] = fa.flatMap(afb)
      def map[A, B](fa: IO[A], ab: A => B): IO[B] = fa.map(ab)
    }
    implicit val ConsoleIO = new Console[IO] {
      def putStrLn(line: String): IO[Unit] = IO(() => println(line))
      def getStrLn: IO[String] = IO(() => readLine())
    }
    implicit val RandomIO = new Random[IO] {
      def nextInt(upper: Int): IO[Int] = IO(() => scala.util.Random.nextInt(upper))
    }
  }

  def checkContinue[F[_]: Program: Console](name: String): F[Boolean] = for {
    _     <- putStrLn(s"Do you want to continue, $name?")
    input <- getStrLn.map(_.toLowerCase)
    cont  <- input match {
      case "y" => finish(true)
      case "n" => finish(false)
      case _ => checkContinue(name)
    }
  } yield cont

  def gameLoop[F[_]: Program: Random: Console](name: String): F[Unit] = for {
    num   <- nextInt(5).map(_ + 1)
    _     <- putStrLn(s"Dear $name, please guess a number from 1 to 5: ")
    input <- getStrLn
    _     <- parseInt(input)
               .fold(putStrLn("You did not enter a number")) { guess =>
                  if (guess == num) putStrLn(s"You guessed right, $name!")
                  else              putStrLn(s"You guessed wrong, $name! The number was: $num")
                }
    cont  <- checkContinue(name)
    _     <- if (cont) gameLoop(name) else finish(())
  } yield ()

  def main[F[_]: Program: Random: Console]: F[Unit] = for {
    _    <- putStrLn("What is your name? ")
    name <- getStrLn
    _    <- putStrLn(s"Hello, $name, welcome to the game!")
    _    <- gameLoop(name)
  } yield ()

  def mainIO: IO[Unit] = main[IO]

  mainIO.unsafeRun
}
