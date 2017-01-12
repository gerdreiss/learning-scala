package com.jscriptive.scala.examples.chapter2

object QuickSort {

  def isort(xs: Array[Int]): Unit = {
    def swap(i: Int, j: Int): Unit = {
      val t = xs(i)
      xs(i) = xs(j)
      xs(j) = t
    }
    def innerSort(l: Int, r: Int): Unit = {
      val pivot = xs((l + r) / 2)
      var i = l
      var j = r
      while (i <= j) {
        while (xs(i) < pivot) i += 1
        while (xs(j) > pivot) j -= 1
        if (i <= j) {
          swap(i, j)
          i += 1
          j -= 1
        }
      }
      if (l < j) innerSort(l, j)
      if (j < r) innerSort(i, r)
    }
    innerSort(0, xs.length - 1)
  }

  def fsort(xs: Array[Int]): Array[Int] = {
    if (xs.length <= 1) xs
    else {
      val pivot = xs(xs.length / 2)
      Array.concat(
        fsort(xs filter (pivot > )),
              xs filter (pivot == ),
        fsort(xs filter (pivot < ))
      )
    }
  }

  def main(args: Array[String]): Unit = {
    val xs = Array(3, 5, 2, -3, 100, -101, -1, 33)
    isort(xs)
    println(xs.toList)
    println(fsort(xs).toList)
  }
}
