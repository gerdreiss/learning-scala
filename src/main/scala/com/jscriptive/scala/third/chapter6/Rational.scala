package com.jscriptive.scala.third.chapter6

class Rational(n: Int, d: Int) extends Ordered[Rational] {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)

  val numer: Int = n / g
  val denom: Int = d / g

  // auxiliary constructor, invoking the main constructor
  def this(n: Int) = this(n, 1)

  def +(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )

  def +(i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational =
    new Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def -(i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def *(that: Rational): Rational =
    new Rational(
      numer * that.numer,
      denom * that.denom
    )

  def *(i: Int): Rational =
    new Rational(numer * i, denom)

  def /(that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def /(i: Int): Rational =
    new Rational(numer, denom * i)

  //def <(that: Rational) =
  //  this.numer * that.denom < that.numer * this.denom
  //def >(that: Rational) =
  //  this.numer * that.denom > that.numer * this.denom

  def max(that: Rational) =
    if (this < that) that else this

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  override def compare(that: Rational): Int =
    (this.numer * that.denom) - (that.numer * this.denom)

  override def toString: String = n + "/" + d
}


object Rational {
  def main(args: Array[String]): Unit = {
    val r1: Rational = new Rational(16, 32)
    val r2: Rational = new Rational(21, 63)
    val r3: Rational = r1 + r2
    val r4 = r3 * r1

    println(r1)
    println(r2)
    println(r1 max r2)
    println(r3)
    println(r4)
    println(r4 + 3)
    println()
    println(r1 < r2)
    println(r1 > r2)
    println(r1 + r2 * r3 == (r1 + r2) * r3)
  }
}