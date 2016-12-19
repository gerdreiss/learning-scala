package com.jscriptive.scala.third.chapter19

object Queues {

  // not very efficient
  class SlowAppendQueue[T](elems: List[T]) {
    def head: T = elems.head
    def tail = new SlowAppendQueue[T](elems.tail)
    def enqueue(x: T) = new SlowAppendQueue[T](elems ::: List(x))
  }

  // not too efficient either
  class SlowHeadQueue[T](smele: List[T]) {
    def head: T = smele.last
    def tail = new SlowHeadQueue[T](smele.init)
    def enqueue(x: T) = new SlowHeadQueue[T](x :: smele)
  }

  // basic functional queue with private primary constructor
  class Queue[T] private(private val leading: List[T],
                         private val trailing: List[T]) {

    private def mirror: Queue[T] =
      if (leading.isEmpty)
        new Queue(trailing.reverse, Nil)
      else this

    def head: T = mirror.leading.head

    def tail: Queue[T] = {
      val q = mirror
      new Queue(q.leading.tail, q.trailing)
    }

    def enqueue(x: T) = new Queue(leading, x :: trailing)
  }

  object Queue {
    // constructs a queue with initial elements 'xs'
    def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
  }

  def main(args: Array[String]): Unit = {
    val q1 = Queue(1, 2, 3)
    val q2 = q1 enqueue 4
    println("q1 == q2 ? -> " + (q1 == q2))
  }
}
