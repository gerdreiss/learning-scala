package com.jscriptive.scala.third.chapter16

object MergeSort {

  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }


  def main(args: Array[String]): Unit = {
    val ints = List(9, 2, 8, 4, 0, 3, 10, 1)

    println(msort((x: Int, y: Int) => x < y)(ints))

    val intSort = msort((x: Int, y: Int) => x < y) _

    println(intSort(ints))

    val intlists = List.range(1, 5) flatMap {
      i => List.range(1, i) map (j => (i, j))
    }
    println(intlists)

    println(ints.sum)
  }
}
