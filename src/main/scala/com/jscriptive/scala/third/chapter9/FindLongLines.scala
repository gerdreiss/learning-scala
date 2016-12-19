package com.jscriptive.scala.third.chapter9

object FindLongLines {

  def main(args: Array[String]): Unit = {
    val width = args(0).toInt
    for (arg <- args.drop(1)) {
      LongLines.processFile(arg, width)
    }
  }
}
