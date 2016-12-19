package com.jscriptive.scala.third.chapter20

object Money {

  object Converter {
    var exchangeRate = Map(
      "USD" -> Map("USD" -> 1.0000, "EUR" -> 0.7596, "JPY" -> 1.2110, "CHF" -> 1.2230),
      "EUR" -> Map("USD" -> 1.3160, "EUR" -> 1.0000, "JPY" -> 1.5940, "CHF" -> 1.6230),
      "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, "JPY" -> 1.0000, "CHF" -> 1.0180),
      "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160, "JPY" -> 0.9820, "CHF" -> 1.0000)
    )
  }

  abstract class CurrencyZone {
    type Currency <: AbstractCurrency

    def make(x: Long): Currency

    abstract class AbstractCurrency {
      val amount: Long

      def designation: String

      def +(that: Currency): Currency =
        make(this.amount + that.amount)
      def *(x: Double): Currency =
        make((this.amount * x).toLong)
      def -(that: Currency): Currency =
        make(this.amount - that.amount)
      def /(x: Double): Currency =
        make((this.amount / x).toLong)

      def from(other: CurrencyZone#AbstractCurrency): Currency =
        make(math.round(
          other.amount.toDouble * Converter.exchangeRate
          (other.designation)(this.designation)
        ))

      private def decimals(n: Long): Int =
        if (n == 1) 0 else 1 + decimals(n / 10)

      override def toString: String =
        (amount.toDouble / CurrencyUnit.amount.toDouble) formatted
          ("%." + decimals(CurrencyUnit.amount) + "f") + " " + designation
    }

    val CurrencyUnit: Currency
  }

  object US extends CurrencyZone {
    type Currency = Dollar

    abstract class Dollar extends AbstractCurrency {
      def designation: String = "USD"
    }

    def make(cents: Long): Dollar = new Dollar {
      val amount: Long = cents
    }

    val Cent: Dollar = make(1)
    val Dollar: Dollar = make(100)
    val CurrencyUnit = Dollar
  }

  object Europe extends CurrencyZone {
    type Currency = Euro

    abstract class Euro extends AbstractCurrency {
      def designation: String = "EUR"
    }

    def make(cents: Long): Euro = new Euro {
      val amount: Long = cents
    }

    val Cent: Euro = make(1)
    val Euro: Euro = make(100)
    val CurrencyUnit = Euro
  }

  object Japan extends CurrencyZone {
    type Currency = Yen

    abstract class Yen extends AbstractCurrency {
      def designation: String = "JPY"
    }

    def make(yen: Long) = new Yen {
      val amount: Long = yen
    }

    val Yen: Yen = make(1)
    val CurrencyUnit = Yen
  }

  def main(args: Array[String]): Unit = {
    println(Japan.Yen from US.Dollar * 100)
    println(Europe.Euro from Japan.Yen * 12110)
  }
}
