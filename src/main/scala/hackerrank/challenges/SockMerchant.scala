package hackerrank.challenges

object SockMerchant extends App {

  def sockMerchant(n: Int, ar: Array[Int]): Int = {
    ar.sorted
      .groupBy(identity)
      .values
      .filter(_.length > 1)
      .map(_.length / 2)
      .sum
  }

  println(sockMerchant(9, Array(10, 20, 20, 10, 10, 30, 50, 10, 20)))
}
