package hackerrank.algorithms.warmup

import java.util.Scanner

object MiniMaxSum {

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val numbers: Seq[BigInt] = (1 to 5).map(_ => BigInt(sc.nextBigInteger())).sorted
    println(s"${numbers.init.sum} ${numbers.tail.sum}")
  }
}
