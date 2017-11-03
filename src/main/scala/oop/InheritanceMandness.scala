package oop

/**
 * Created by grei on 03.11.2017
 */
object InheritanceMandness extends App {

  trait T1 {
    def m1: String
  }

  class C1 extends T1 {
    override def m1: String = "???"
  }

  trait T2 extends C1

  println(new T2{}.m1)
}
