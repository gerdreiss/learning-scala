package com.jscriptive.scala.third.chapter33

import scala.io.StdIn
import scala.util.parsing.combinator._

class Arith extends JavaTokenParsers {

  def expr: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term)
  def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)
  def factor: Parser[Any] = floatingPointNumber | "(" ~ term ~ ")"

}

object ParseExpr extends Arith {
  def main(args: Array[String]): Unit = {
    val input = StdIn.readLine("Enter your expression: ")
    println(parseAll(expr, input))
  }
}
