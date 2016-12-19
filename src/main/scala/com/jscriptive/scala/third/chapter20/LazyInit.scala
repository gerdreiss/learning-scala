package com.jscriptive.scala.third.chapter20

object LazyInit {

  object Demo {
    lazy val x: String = {
      println("initializing x")
      "done"
    }
  }

  def main(args: Array[String]): Unit = {
    println("Print Demo:")
    println(Demo)

    println("Print Demo.x:")
    println(Demo.x)
  }

}
