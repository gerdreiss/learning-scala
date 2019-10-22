package typesystem.implicits

import scala.language.implicitConversions

object ComplexNumbers extends App {

  case class Complex(real: Double, imaginary: Double = 0.0) {
    override def toString: String = s"$real $sign ${imaginary.abs}i"
    private def sign = if (imaginary >= 0.0) "+" else "-"

    def +(other: Complex): Complex =
      Complex(real + other.real, imaginary + other.imaginary)
  }
  object Complex {
    implicit def intToComplex(i: Int): Complex = Complex(i)
  }

  println(1 + Complex(3))
  println(Complex(3) + 1)
}
