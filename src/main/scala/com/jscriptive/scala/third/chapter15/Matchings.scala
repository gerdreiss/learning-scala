package com.jscriptive.scala.third.chapter15

import scala.math.{E, Pi}

object Matchings {

  def main(args: Array[String]): Unit = {

    val pi = Pi
    var result = E match {
      case `pi` => "strange math? Pi = " + pi
      case _ => "no idea..."
    }
    println(result)

    val ints = List(0, 1, 2)
    result = ints match {
      case List(0, _, _) => "found the list"
      case _ => "where's the list?"
    }
    println(result)

    result = ints match {
      case List(0, _*) => "found the list again"
      case _ => "still looking for the list..."
    }
    println(result)

    val anything: Any = result
    result = anything match {
      case s: String => "found a String of length " + s.length
      case m: Map[_, _] => "found a Map of size " + m.size
      case l: List[_] => "found a List of elements starting with " + l.head
      case _ => "no idea what that is..."
    }
    println(result)

    val withDefault: Option[Int] => Int = {
      case Some(x) => x
      case None => 0
    }
    println(withDefault(Some(11)))
    println(withDefault(None))

    // This is an invalid partial matching definition
    val sum1and2: List[Int] => Int = {
      case x :: y :: _ => x + y
    }
    println(sum1and2(List(1, 2, 3)))

    // This is a valid partial matching definition
    val second: PartialFunction[List[Int], Int] = {
      case _ :: y :: _ => y
    }
    println(second(List(1, 2, 3)))
    println(second.isDefinedAt(List(4, 5, 6)))
    println(second.isDefinedAt(List()))
  }
}
