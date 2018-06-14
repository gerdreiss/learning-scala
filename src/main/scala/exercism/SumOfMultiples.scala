package exercism

import scala.language.postfixOps

object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int =
    (1 until limit) filter { n =>
      factors exists (n % _ == 0)
    } sum

  def sum2(factors: Set[Int], limit: Int): Int =
    (for {
      f <- factors
      n <- 1 until limit
      if f * n < limit
    } yield f * n) sum

}

