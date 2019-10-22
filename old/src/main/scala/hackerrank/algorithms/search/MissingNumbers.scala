package hackerrank.algorithms.search

object MissingNumbers {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val A: Map[Int, Int] = (1 to sc.nextInt).map(_ => sc.nextInt).groupBy(identity).mapValues(_.size)
    val B: Map[Int, Int] = (1 to sc.nextInt).map(_ => sc.nextInt).groupBy(identity).mapValues(_.size)
    val missing = B.toSet.diff(A.toSet).map(_._1).toSeq.sorted
    println(missing.mkString(" "))
  }
}
