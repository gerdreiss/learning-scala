package com.jscriptive.scala.third.chapter19

object CovariantQueue {

  class Queue[+T](private[this] var leading: List[T],
                  private[this] var trailing: List[T]) {

    private def mirror() =
      if (leading.isEmpty) {
        while (trailing.nonEmpty) {
          leading = trailing.head :: leading
          trailing = trailing.tail
        }
      }

    def head: T = {
      mirror()
      leading.head
    }

    def tail: Queue[T] = {
      mirror()
      new Queue(leading.tail, trailing)
    }

    def enqueue[U >: T](x: U) = new Queue[U](leading, x :: trailing)

    override def toString: String = leading.toString() + ": " + trailing
  }

  abstract class Fruit {
    override def toString: String = "Some Fruit"
  }

  class Apple extends Fruit {
    override def toString: String = "Apple"
  }

  class Orange extends Fruit {
    override def toString: String = "Orange"
  }

  def main(args: Array[String]): Unit = {
    val q1: Queue[Apple] = new Queue[Apple](List(new Apple), List())
    val q2: Queue[Apple] = q1 enqueue new Apple
    val q3: Queue[Fruit] = q2 enqueue new Orange
    println(q1)
    println(q2)
    println(q3)
  }
}
