package exercism

object CollatzConjecture {

  // Take any positive integer n.
  // If n is even, divide n by 2 to get n / 2.
  // If n is odd, multiply n by 3 and add 1 to get 3n + 1.
  def steps(n: Int): Option[Int] =
    if (n <= 0) None
    else if (n == 1) Some(0)
    else if (n % 2 == 0) steps(n / 2).map(_ + 1)
    else steps(n * 3 + 1).map(_ + 1)

  // Beautiful solution, not mine
  def steps0(n: Int): Option[Int] = {
    def nextNum(x: Int) =
      if (x % 2 == 0) x / 2 else x * 3 + 1

    Option(n).filter(_ > 0).map {
      Stream.iterate(_)(nextNum)
        .takeWhile(_ != 1)
        .length
    }
  }

}
