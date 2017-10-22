package fp.simplified

/**
 * Created by grei on 18.10.2017
 */
object ControlStructureUsingThreeParamGroups extends App {

  def ifBothTrue(p1: => Boolean)(p2: => Boolean)(f: => Unit): Unit = {
    if (p1 && p2) f
  }

  ifBothTrue(p1 = true)(p2 = true) {
    println("both true")
  }
}
