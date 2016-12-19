package com.jscriptive.scala.third.chapter19

object ContravariantChannel {

  trait OutputChannel[-T] {
    def write(x: T)
  }

  trait Function1[-S, +T] {
    def apply(x: S): T
  }
}
