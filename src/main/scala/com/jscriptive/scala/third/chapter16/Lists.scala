package com.jscriptive.scala.third.chapter16

object Lists {

  def append[T](xs: List[T], ys: List[T]): List[T] =
    xs match {
      case List() => ys
      case x :: xs1 => x :: append(xs1, ys)
    }

  def reverse[T](xs: List[T]): List[T] =
    xs match {
      case List() => xs
      case x :: xs1 => reverse(xs1) ::: List(x)
    }

  def reverseLeft[T](xs: List[T]): List[T] =
    (List[T]() /: xs) { (ys, y) => y :: ys }


  def main(args: Array[String]): Unit = {
    val ints = append(List(1, 2, 3), List(4, 5, 6, 0))
    println(ints)
    println(reverse(ints))

    val twodiml = List(List(1, 2), List(3), List(), List(4, 5))
    println(twodiml.flatten)

    val strings = List("apples", "oranges", "pears")
    println(strings.flatten)
    println(strings sortWith (_.compareTo(_) > 0))

    println(List.tabulate(5)(n => n * n + 1))
  }
}
