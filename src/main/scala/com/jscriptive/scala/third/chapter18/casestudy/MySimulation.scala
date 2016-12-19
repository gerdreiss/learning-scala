package com.jscriptive.scala.third.chapter18.casestudy

object MySimulation extends CircuitSimulation {

  override def InverterDelay: Int = 1
  override def AndGateDelay: Int = 3
  override def OrGateDelay: Int = 5

  def main(args: Array[String]): Unit = {
    val input1, input2, sum, carry = new Wire
    probe("sum", sum)
    probe("carry", carry)
    halfAdder(input1, input2, sum, carry)
    input1 setSignal true
    run()
    input2 setSignal true
    run()
  }
}
