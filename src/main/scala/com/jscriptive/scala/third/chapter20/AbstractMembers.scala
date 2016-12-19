package com.jscriptive.scala.third.chapter20

object AbstractMembers {

  trait Abstract {
    type T
    def transform(x: T): T
    val initial: T
    var current: T
  }

  class Concrete extends Abstract {
    override type T = String
    override def transform(x: String): String = x + x
    override val initial: String = "hi"
    override var current: String = initial
  }

}
