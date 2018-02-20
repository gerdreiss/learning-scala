package neophytesguide

import scala.util.Random

trait NumberLike[T] {
  def plus(x: T, y: T): T
  def minus(x: T, y: T): T
  def multiply(x: T, y: Int): T
  def divide(x: T, y: Int): T
}
object NumberLike {
  implicit object NumberLikeDouble extends NumberLike[Double] {
    def plus(x: Double, y: Double): Double = x + y
    def minus(x: Double, y: Double): Double = x - y
    def multiply(x: Double, y: Int): Double = x * y
    def divide(x: Double, y: Int): Double = x / y
  }
  implicit object NumberLikeInt extends NumberLike[Int] {
    def plus(x: Int, y: Int): Int = x + y
    def minus(x: Int, y: Int): Int = x - y
    def multiply(x: Int, y: Int): Int = x * y
    def divide(x: Int, y: Int): Int = x / y
  }
}

object Statistics {
  def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T =
    ev.divide(xs.reduce(ev.plus), xs.size)
}

object TypeClasses extends App {
  val numbers = //Vector[Double](13, 23.0, 42, 45, 61, 73, 96, 100, 199, 420, 900, 3839)
    (1 to Random.nextInt(100))
      .map(_ => Random.nextDouble())
      .map(_ * math.pow(10, Random.nextInt(8)))
      .toVector

  println(s"The numbers:\n\t${numbers.mkString(", ")}")
  println(s"The mean:\n\t${Statistics.mean(numbers)}")
}
