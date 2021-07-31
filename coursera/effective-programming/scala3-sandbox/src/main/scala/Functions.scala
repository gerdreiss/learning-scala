import scala.annotation.tailrec
def add(x: Int, y: Int): Int = x + y

def fibonacci(n: Int): Int =
  @tailrec
  def fib_tail(n: Int, a: Int, b: Int): Int =
    if n == 0 then a else fib_tail(n - 1, b, a + b)

  fib_tail(n, 0, 1)
