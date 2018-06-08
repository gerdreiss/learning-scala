package codefights

object AdjacentElementsProduct extends App {

  def adjacentElementsProduct(inputArray: Array[Int]): Int =
    (inputArray zip inputArray.tail)
      .map {
        case (a, b) => a * b
      }.max

  println(adjacentElementsProduct(Array(3, 6, -2, -5, 7, 3)))

}
