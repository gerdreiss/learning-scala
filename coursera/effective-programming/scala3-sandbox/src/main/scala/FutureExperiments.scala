import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.util.{Try, Using}


def experiment1: Unit =
  val fa = Future(println("A"))
  val fb = Future(println("B"))
  for
    _ <- fa
    _ <- fb
  yield ()

def experiment2: Unit =
  def fa() = Future(println("A"))
  def fb() = Future(println("B"))
  for
    _ <- fa()
    _ <- fb()
  yield ()

def experiment3: Unit =
  for
    _ <- Future(println("A"))
    _ <- Future(println("B"))
  yield ()

@main def runFutureExperiments(): Unit =
  println("-" * 50)
  println("Experiment 1")
  experiment1
  println("-" * 50)
  println("Experiment 2")
  experiment2
  println("-" * 50)
  println("Experiment 3")
  experiment3
