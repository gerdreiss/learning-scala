package tlp

import scala.language.implicitConversions

object Implicits extends App {

  final case class FinalClass(s: String)

  implicit class ImplClass(fc: FinalClass) {
    def func(): FinalClass = {
      fc
    }
  }

  val fcc = FinalClass("")
  fcc.func()

  implicit def agentCodename(i: Int) = s"00$i"

  def helloS(name: String) = s"Hello, $name!"
  helloS(7)
  // "Hello, 007!"

  implicit class Agent(code: Int) {
    def codename = s"00$code"
  }

  def helloA(agent: Agent) = s"Hello, ${agent.codename}!"
  println(helloA(7))
  // "Hello, 007!"

  implicit class StringDec(val s: String) {
    def specialFunc()= s"$s$s$s"
  }

  println("X".specialFunc())

  def printS(s: String) = println(s)
  printS(7)

}
