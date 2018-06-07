import scala.language.postfixOps

object SumOfMultiples {
  def sum(factors: Set[Int], limit: Int): Int =
    (1 to limit) filter { n =>
      factors exists(n % _ == 0)
    } sum
}

