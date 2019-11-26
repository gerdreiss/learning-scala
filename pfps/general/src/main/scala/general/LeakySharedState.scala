package general

import cats.effect.concurrent.Semaphore
import cats.effect._
import cats.effect.implicits._
import cats.implicits._

import cats.effect.Console.io._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

object LeakySharedState extends IOApp {
  // global access
  val sem: Semaphore[IO] = Semaphore[IO](1).unsafeRunSync()

  def someExpensiveTask(no: Int): IO[Unit] =
    IO.sleep(1.second) >> putStrLn(s"expensive task $no") >> someExpensiveTask(no)

  //new LaunchMissiles(sem).run // Unit

  def p1: IO[Unit] =
    sem.withPermit(someExpensiveTask(1)) >> p1

  def p2: IO[Unit] =
    sem.withPermit(someExpensiveTask(2)) >> p2

  def run(args: List[String]): IO[ExitCode] =
    p1.start.void *> p2.start.void *> IO.never.as(ExitCode.Success)

}
