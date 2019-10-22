package codefights


object ShapeArea extends App {
  def shapeArea(n: Int): Int = {
    n * n + (n - 1) * (n - 1)
  }

  println(s"n = 1, area = ${shapeArea(1)}")
  println(s"n = 2, area = ${shapeArea(2)}")
  println(s"n = 3, area = ${shapeArea(3)}")
  println(s"n = 4, area = ${shapeArea(4)}")
  println(s"n = 5, area = ${shapeArea(5)}")

}
