package third.chapter22

import scala.collection.mutable.ListBuffer

/**
  * Created by igor on 22.12.16.
  */
object Lists {

  // very inefficient way to increment all integers in a list
  def incrementAllRecursively(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs1 => x + 1 :: incrementAllRecursively(xs1)
  }

  // another very inefficient approach
  def incrementAllUsingNewList(xs: List[Int]): List[Int] = {
    var result = List[Int]()
    for (x <- xs) result = result ::: List(x + 1) // <-- this is very inefficient
    result
  }

  // better approach
  def incrementAllUsingListBuffer(xs: List[Int]): List[Int] = {
    val buf = new ListBuffer[Int]
    for (x <- xs) buf += x + 1 // this works well and fast
    buf.toList
  }

  def main(args: Array[String]): Unit = {
    // wha' eva
  }
}
