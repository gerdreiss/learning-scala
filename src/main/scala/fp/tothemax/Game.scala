package fp.tothemax

import scala.util.Try
import scala.language.higherKinds

import Algebra._
import TestAlgebra._

/**
  * Programming a guessing game with John de Goes:
  * https://www.youtube.com/watch?v=sxudIMiOo68
  */
object Game extends App {

  sealed trait ConsoleOut {
    def en: String
  }
  object ConsoleOut {
    case class YouGuessedRight(name: String) extends ConsoleOut {
      def en = s"You guessed right, $name!"
    }
    case class YouGuessedWrong(name: String, num: Int) extends ConsoleOut {
      def en = s"You guessed wrong, $name! The number was: $num"
    }
    case class DoYouWantToContinue(name: String) extends ConsoleOut {
      def en = s"Do you want to continue, $name?"
    }
    case class PleaseGuess(name: String) extends ConsoleOut {
      def en = s"Dear $name, please guess a number from 1 to 5: "
    }
    case class ThatIsNotValid(name: String) extends ConsoleOut {
      def en = s"That is not a valid selection, $name!"
    }
    case object WhatIsYourName extends ConsoleOut {
      def en = "What is your name?"
    }
    case class WelcomeToGame(name: String) extends ConsoleOut {
      def en = s"Hello, $name, welcome to the game!"
    }
    case object NotANumber extends ConsoleOut {
      def en = "You did not enter a number"
    }
  }

  def parseInt(s: String): Option[Int] = Try(s.toInt).toOption

  def finish[F[_]: Program, A](a: => A): F[A] = Program[F].finish(a)
  def putStrLn[F[_]: Console](line: String): F[Unit] = Console[F].putStrLn(line)
  def getStrLn[F[_]: Console]: F[String] = Console[F].getStrLn
  def nextInt[F[_]: Random](upper: Int): F[Int] = Random[F].nextInt(upper)

  def printResults[F[_]: Console](name: String, num: Int, input: String): F[Unit] = {
    parseInt(input)
      .fold(putStrLn("You did not enter a number")) { guess =>
        if (guess == num) putStrLn(s"You guessed right, $name!")
        else putStrLn(s"You guessed wrong, $name! The number was: $num")
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
    _     <- printResults(name, num, input)
    cont  <- checkContinue(name)
    _     <- if (cont) gameLoop(name) else finish(())
  } yield ()

  def main[F[_]: Program: Random: Console]: F[Unit] = for {
    _    <- putStrLn("What is your name? ")
    name <- getStrLn
    _    <- putStrLn(s"Hello, $name, welcome to the game!")
    _    <- gameLoop(name)
  } yield ()

  // RUNNING
  def mainIO: IO[Unit] = main[IO]

  mainIO.unsafeRun

  // TESTING
  val TestExample = TestData(
    input = "John" :: "1" :: "n" :: Nil,
    output = Nil,
    nums = 0 :: Nil
  )

  def mainTestIO: TestIO[Unit] = main[TestIO]

  def runTest = mainTestIO.eval(TestExample).showResults

  println(runTest)
}
