package com.jscriptive.scala.fp.chapter1

class Coffee(val flavor: String = "espresso", val price: Int = 500 /* cents */)

class CreditCard(val number: String) {
  def charge(amount: Int): Unit =
    println(s"Charging $amount")

  override def toString: String = number
}

class Payments {
  def charge(cc: CreditCard, price: Int): Unit =
    println(s"Charging creditcard $cc $price cents")
}

case class Charge(cc: CreditCard, amount: Int) {
  def combine(other: Charge): Charge =
    if (cc == other.cc)
      Charge(cc, amount + other.amount)
    else
      throw new Exception("Can't combine charge to different cards")
}

class CafeWithSideEffects {
  def buyCoffee(cc: CreditCard, p: Payments): Coffee = {
    val cup = new Coffee()
    p.charge(cc, cup.price) // <- side effect
    cup
  }
}

class CafeWithoutSideEffects {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val cup = new Coffee()
    (cup, Charge(cc, cup.price))
  }

  def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
    val (coffees: List[Coffee], charges: List[Charge]) = purchases.unzip
    (coffees, charges.reduce(_ combine _))
  }

  def coalesce(charges: List[Charge]): List[Charge] =
    charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
}

