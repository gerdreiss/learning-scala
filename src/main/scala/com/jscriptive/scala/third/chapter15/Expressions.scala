package com.jscriptive.scala.third.chapter15

object Expressions {

  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => e // Double negation
    case UnOp("abs", e@UnOp("abs", _)) => e // Double abs
    case BinOp("+", e, Number(0)) => e // Adding zero
    case BinOp("*", e, Number(1)) => e // Multiplying by one
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) // with pattern guard
    case _ => expr
  }

  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => simplifyAll(e)
    case BinOp("+", e, Number(0)) => simplifyAll(e)
    case BinOp("*", e, Number(1)) => simplifyAll(e)
    case UnOp(op, e) => UnOp(op, simplifyAll(e))
    case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  }

  def describe(expr: Expr): String = (expr: @unchecked) match {
    case Number(n) => "number " + n
    case Var(v) => "variable " + v
    // instead of throwing the exception like below,
    // add @unchecked to the thing you're trying to match, like above
    // case _ => throw new IllegalArgumentException(
    //   "Cannot describe expressions of type "
    //     + expr.getClass.getSimpleName)
  }

  def main(args: Array[String]): Unit = {
    println(describe(Var("x")))
    println(describe(Number(1)))
    //println(describe(UnOp("-", Number(1))))
    println

    // How awesome is that?
    val expr = BinOp("*", Number(5), Number(2))
    val BinOp(op, left, right) = expr
    println("Operation : " concat op)
    println("     Left : " concat left.toString)
    println("    Right : " concat right.toString)
  }
}
