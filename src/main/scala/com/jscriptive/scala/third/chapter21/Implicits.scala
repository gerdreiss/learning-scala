package com.jscriptive.scala.third.chapter21

object Implicits {

  def maxList[T: Ordering](elements: List[T]): Option[T] =
    elements match {
      case List() => None
      case List(x) => Some(x)
      case x :: rest =>
        val maxRest = maxList(rest).get
        if (implicitly[Ordering[T]].gt(x, maxRest)) Some(x)
        else Some(maxRest)
    }

  def main(args: Array[String]): Unit = {
    println(maxList(List[String]()))
    println(maxList(List('x')))
    println(maxList(List(1, 10, 2, 23, 100.0, 22, -1000)))
  }
}
