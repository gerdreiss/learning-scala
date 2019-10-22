package effective

import scala.annotation.tailrec

object Factorials {

  // Loopy loops
  def factSeq0(n: Int): List[Long] = {
    def fact(v: Int): Long = {
      var prod = 1L
      for (i <- 1 to v) prod *= i
      prod
    }
    val buf = scala.collection.mutable.ArrayBuffer.empty[Long]
    for (i <- 1 to n) buf.append(fact(i))
    buf.toList
  }

  // Recursion
  @tailrec
  def factSeq1(lim: Int, cur: Int = 2, xs: List[Long] = List(1L)): List[Long] = {
    if (cur > lim) xs.reverse
    else factSeq1(lim, cur + 1, (cur * xs.head) :: xs)
  }


  def main(args: Array[String]): Unit = {
    println(factSeq0(10))
    println(factSeq1(10))
  }
}
