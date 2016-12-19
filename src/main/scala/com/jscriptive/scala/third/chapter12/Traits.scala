package com.jscriptive.scala.third.chapter12

object Traits {

  trait Philosophical {
    def philosophize(): Unit = {
      println("I consume memory, therefore I am!")
    }
  }

  class Animal

  trait HasLegs

  class Frog extends Animal with Philosophical with HasLegs {
    override def toString: String = "green"

    override def philosophize(): Unit = {
      println("It ain't easy being " + toString + "!")
    }
  }

  def main(args: Array[String]): Unit = {
    val frog = new Frog
    frog.philosophize()

    val phil: Philosophical = frog
    phil.philosophize()
  }
}
