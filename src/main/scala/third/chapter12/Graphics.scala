package third.chapter12

object Graphics {

  class Point(val x: Int, y: Int)

  trait Rectangular {

    def topLeft: Point

    def bottomRight: Point

    def left: Int = topLeft.x

    def right: Int = bottomRight.x

    def width: Int = right - left
  }

  class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {
  }

  def main(args: Array[String]): Unit = {
    val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
    println(rect.left)
    println(rect.right)
    println(rect.width)
  }
}
