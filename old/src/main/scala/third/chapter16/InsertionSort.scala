package third.chapter16

object InsertionSort {

  def isortrec(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insertrec(xs.head, isortrec(xs.tail))

  def insertrec(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insertrec(x, xs.tail)


  def isortmatch(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: rest => insertmatch(x, isortmatch(rest))
  }

  def insertmatch(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: rest => {
      if (x <= y) x :: xs
      else y :: insertmatch(x, rest)
    }
  }

  def main(args: Array[String]): Unit = {
    val li = List(1, 2) ::: List(2, 3, 4)
    println(li)
    val rev = li.reverse
    println(rev)
    val sorted1 = isortrec(rev)
    val sorted2 = isortmatch(rev)
    println(sorted1)
    println(sorted2)
  }
}
