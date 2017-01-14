package com.jscriptive.scala.fp.chapter2

object Sorted {

  def isSorted1[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    as.sortWith(ordered) sameElements as
  }

  def isSorted2[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    def loop(n: Int): Boolean =
      if (n == as.length - 1) true
      else if (!ordered(as(n), as(n + 1))) false
      else loop(n + 1)
    loop(0)
  }

  def main(args: Array[String]): Unit = {
    println(isSorted1(Array(0, 1, 3, 13, 11), (a: Int, b: Int) => a <= b))
    println(isSorted2(Array(0, 1, 3, 13, 11), (a: Int, b: Int) => a <= b))
  }
}
