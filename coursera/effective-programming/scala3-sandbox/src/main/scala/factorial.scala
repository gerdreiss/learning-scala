import scala.annotation.tailrec
object factorial:

  def by_product(n: Int): Int =
    (1 to n).product

  def by_foldLeft(n: Int): Int =
    (1 to n).foldLeft(1)((acc, x) => acc * x)

  def by_while_do(n: Int): Int =
    var acc = 1
    var i   = 1
    while i < n do
      i = i + 1
      acc = acc * i
    acc

  def by_recursion(n: Int): Int =
    n match
      case 0 => 1
      case _ => n * by_recursion(n - 1)

  def by_recursion_tailrec(n: Int): Int =
    @tailrec
    def fac_tailrec(x: Int, acc: Int): Int =
      if x == 0 then acc
      else fac_tailrec(x - 1, x * acc)

    fac_tailrec(n, 1)
  end by_recursion_tailrec
