package general

import cats.effect.concurrent.Semaphore
import cats.effect._
import cats.effect.implicits._
import cats.implicits._

import cats.effect.Console.io._

import scala.concurrent.duration._
import scala.language.postfixOps

object SharedState extends IOApp {

  def someExpensiveTask(no: Int): IO[Unit] =
    IO.sleep(1 second) >>
        putStrLn(s"expensive task $no") >>
        someExpensiveTask(no)

  def p1(sem: Semaphore[IO]): IO[Unit] =
    sem.withPermit(someExpensiveTask(1)) >> p1(sem)

  def p2(sem: Semaphore[IO]): IO[Unit] =
    sem.withPermit(someExpensiveTask(2)) >> p2(sem)

  def run(args: List[String]): IO[ExitCode] =
    Semaphore[IO](2).flatMap { sem =>
      p1(sem).start.void *>
        p2(sem).start.void
    } *> IO.never.as(ExitCode.Success)
}
