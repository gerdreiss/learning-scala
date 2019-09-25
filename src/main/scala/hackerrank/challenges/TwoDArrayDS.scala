package hackerrank.challenges

object TwoDArrayDS extends App {

  def hourglassSum(arr: Array[Array[Int]]): Int = ???

  val matrix = List.fill(6)(List(1, 2, 3, 4, 5, 6))
    println(matrix.map(_.map(_.toString).mkString(" ")).mkString("\n"))

  val matrices1 = matrix.map(_.sliding(3).toList).sliding(3).toList
  println(matrices1.map(_.map(_.map(_.toString).mkString(" ")).mkString("\n")).mkString("\n"))


  // TODO


}
