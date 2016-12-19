package com.jscriptive.scala.third.chapter10

import com.jscriptive.scala.third.chapter10.Element.elem
import org.scalatest.FunSuite


class ElementSuite extends FunSuite {

  test("elem result should have passed width") {
    val elm = elem('x', 2, 3)
    assert(elm.width == 2)
    assertResult(2) {
      elm.width
    }
    assertThrows[IllegalArgumentException] {
      elem('x', -2, 3)
    }
  }

  test("random stuff") {
    val caught =
      intercept[ArithmeticException] {
        1 / 0
      }

    assert(caught.getMessage == "/ by zero")
  }
}
