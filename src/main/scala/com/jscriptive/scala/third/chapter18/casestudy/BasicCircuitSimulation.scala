package com.jscriptive.scala.third.chapter18.casestudy

abstract class BasicCircuitSimulation extends Simulation {

  def InverterDelay: Int
  def AndGateDelay: Int
  def OrGateDelay: Int

  class Wire {

    private var sigVal: Boolean = false
    private var actions: List[Action] = List()

    def getSignal: Boolean = sigVal
    def setSignal(s: Boolean): Unit =
      if (s != sigVal) {
        sigVal = s
        actions foreach (_ ())
      }

    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }
  }

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction() = {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSig
      }
    }
    input addAction invertAction
  }

  def andGate(a1: Wire, a2: Wire, output: Wire): Unit = {
    def andAction() = {
      def a1Sig = a1.getSignal
      def a2Sig = a2.getSignal
      afterDelay(AndGateDelay) {
        output setSignal (a1Sig & a2Sig)
      }
    }
    a1 addAction andAction
    a2 addAction andAction
  }

  def orGate(a1: Wire, a2: Wire, output: Wire): Unit = {
    def orAction() = {
      def a1Sig = a1.getSignal
      def a2Sig = a2.getSignal
      afterDelay(OrGateDelay) {
        output setSignal (a1Sig | a2Sig)
      }
    }
    a1 addAction orAction
    a2 addAction orAction
  }

  def probe(name: String, wire: Wire): Unit = {
    def probeAction() = {
      println(name + " " + currentTime + " new-value = " + wire.getSignal)
    }
    wire addAction probeAction
  }
}
