package com.jscriptive.scala.third.chapter12

import scala.collection.mutable.ArrayBuffer

object Queues {

  abstract class IntQueue {
    def get(): Int
    def put(x: Int)
  }

  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]

    override def get(): Int = {
      buf.remove(0)
    }

    override def put(x: Int): Unit = {
      buf += x
    }
  }

  trait Doubling extends IntQueue {
    abstract override def put(x: Int): Unit = {
      super.put(2 * x)
    }
  }

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int): Unit = {
      super.put(x + 1)
    }
  }

  trait Filtering extends IntQueue {
    abstract override def put(x: Int): Unit = {
      if (x >= 0) super.put(x)
    }
  }

  class MyQueue extends BasicIntQueue with Doubling

  def main(args: Array[String]): Unit = {
    // Priority of traits goes from right to left!!!!
    val queue = new BasicIntQueue with Filtering with Incrementing with Doubling
    queue.put(42)
    queue.put(10)
    queue.put(20)
    println(queue.get())
    println(queue.get())
    println(queue.get())
  }
}
