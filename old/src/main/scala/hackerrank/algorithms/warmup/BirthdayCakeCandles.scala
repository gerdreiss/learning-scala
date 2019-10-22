package hackerrank.algorithms.warmup

import java.util.Scanner

object BirthdayCakeCandles {

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val heights: Seq[Int] = (1 to sc.nextInt).map(_ => sc.nextInt)
    val highest = heights.max
    val count = heights.count(_ == highest)
    println(s"$highest: $count")
  }
}
