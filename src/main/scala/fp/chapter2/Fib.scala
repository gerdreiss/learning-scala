package fp.chapter2

import scala.annotation.tailrec

object Fib {

  def fib(n: Int): Int = {
    @tailrec def rec(counter: Int = 1, prev: Int = 0, curr: Int = 1): Int =
      if (counter == n) prev
      else rec(counter + 1, curr, prev + curr)

    rec()
  }

  def main(args: Array[String]): Unit = {
    println(fib(6))
  }
}
