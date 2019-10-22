package codefights

object AlmostIncreasingSequence extends App {

  def almostIncreasingSequence(sequence: Array[Int]): Boolean = {
    val result = sequence.head :: sequence.sliding(2)
      .flatMap {
        case Array(x, y) => if (x + 1 == y) Array(y) else Array.empty[Int]
      }.toList
    println(sequence.mkString(", "))
    println(result.mkString(", "))

    true
  }

  println(almostIncreasingSequence(Array(1, 2)))
  println(almostIncreasingSequence(Array(1, 2, 3, 4, 5, 6, 7, 8, 9)))
  println(almostIncreasingSequence(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 1)))
  println(almostIncreasingSequence(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2)))
  println(almostIncreasingSequence(Array(1, 2, 3, 4, 5, 6, 6, 7, 8, 9)))
  println(almostIncreasingSequence(Array(1, 2, 3, 4, 5, 6, 0, 7, 8, 9)))
  println(almostIncreasingSequence(Array(1, 3, 2, 1)))
  println(almostIncreasingSequence(Array(1, 3, 2)))
  println(almostIncreasingSequence(Array(0, -2, 5, 6)))
  println(almostIncreasingSequence(Array(40, 50, 60, 10, 20, 30)))
  println(almostIncreasingSequence(Array(1, 4, 10, 4, 2)))


}
