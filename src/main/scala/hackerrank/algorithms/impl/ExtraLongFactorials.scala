package hackerrank.algorithms.impl

import java.util.Scanner

object ExtraLongFactorials {

  def main(args: Array[String]): Unit = {
    val sc = new Scanner(System.in)
    val n = sc.nextInt
    println(fac(n))
  }

  def fac(n: Int): BigInt = n match {
    case 0 => BigInt(1)
    case _ => BigInt(n) * fac(n - 1)
  }
}
