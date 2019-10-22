package neophytesguide

import java.util.concurrent.TimeUnit.SECONDS

import scala.concurrent.duration.{Duration, FiniteDuration}

object Math {

  import annotation.implicitNotFound

  @implicitNotFound("No member of type class NumberLike in scope for ${T}")
  trait NumberLike[T] {
    def plus(x: T, y: T): T
    def minus(x: T, y: T): T
    def divide(x: T, y: Int): T
  }

  object NumberLike {

    implicit object NumberLikeDouble extends NumberLike[Double] {
      def plus(x: Double, y: Double): Double = x + y
      def minus(x: Double, y: Double): Double = x - y
      def divide(x: Double, y: Int): Double = x / y
    }

    implicit object NumberLikeInt extends NumberLike[Int] {
      def plus(x: Int, y: Int): Int = x + y
      def minus(x: Int, y: Int): Int = x - y
      def divide(x: Int, y: Int): Int = x / y
    }

    implicit object NumberLikeDur extends NumberLike[FiniteDuration] {
      def plus(x: FiniteDuration, y: FiniteDuration): FiniteDuration = x + y
      def minus(x: FiniteDuration, y: FiniteDuration): FiniteDuration = x - y
      def divide(x: FiniteDuration, y: Int): FiniteDuration = Duration.apply(x._1 / y, x._2)
    }

  }

}


object Statistics {

  import Math.NumberLike

  def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T =
    ev.divide(xs.reduce(ev.plus), xs.size)

  def median[T: NumberLike](xs: Vector[T]): T =
    xs(xs.size / 2)

  def quartiles[T: NumberLike](xs: Vector[T]): (T, T, T) =
    (xs(xs.size / 4), median(xs), xs(xs.size / 4 * 3))

  def iqr[T: NumberLike](xs: Vector[T]): T = quartiles(xs) match {
    case (lowerQuartile, _, upperQuartile) =>
      implicitly[NumberLike[T]].minus(upperQuartile, lowerQuartile)
  }
}


object TypeClasses extends App {

  import scala.util.Random

  val numbers = //Vector[Double](13, 23.0, 42, 45, 61, 73, 96, 100, 199, 420, 900, 3839)
    (1 to Random.nextInt(10) + 2)
      .map(_ => Random.nextDouble())
      .map(_ * math.pow(10, Random.nextInt(8)))
      .toVector

  println(s"The numbers:\n\t${numbers.mkString(", ")}")
  println(s"The mean:\n\t${Statistics.mean(numbers)}")


  val durations =
    (1 to Random.nextInt(10) + 2)
    .map(_ => Duration.apply(Random.nextInt(60), SECONDS))
    .toVector

  println(s"The durations:\n\t${durations.mkString(", ")}")
  println(s"The mean:\n\t${Statistics.mean(durations)}")
}
