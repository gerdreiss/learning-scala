package com.jscriptive.scala.third.chapter4

import com.jscriptive.scala.third.chapter4.ChecksumAccumulator.calculate

object FAllWinterSpringSummer extends App {

  for (season <- List("fall", "winter", "spring", "summer")) {
    println(season + ": " + calculate(season))
  }

  println(
    """|Welcome to Ultamix 3000.
       |Type "HELP" for help.""".stripMargin)


  val name = "reader"
  println(s"Hello, $name!")

  println(raw"No\\\\escape!")
  println("\\\\escape!")

  println(f"${math.Pi}%.16f")
}
