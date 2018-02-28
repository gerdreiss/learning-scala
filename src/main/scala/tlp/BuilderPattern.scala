package tlp

import scala.language.implicitConversions


object BuilderPattern extends App {

  sealed abstract class Preparation
  case object Neat extends Preparation {
    override def toString: String = "neat"
  }
  case object OnTheRocks extends Preparation {
    override def toString: String = "on the rocks"
  }
  case object WithWater extends Preparation {
    override def toString: String = "with water"
  }

  sealed abstract class Glass
  case object Short extends Glass
  case object Tall extends Glass
  case object Tulip extends Glass

  case class OrderOfScotch(brand: String, mode: Preparation, isDouble: Boolean, glass: Option[Glass]) {
    private implicit class B2S(b: Boolean) {
      def b2s: String = if (b) "double" else ""
    }
    private implicit class G2S(g: Option[Glass]) {
      def g2s: String = g.map("in a " + _.toString.toLowerCase + " glass").getOrElse("")
    }
    override def toString: String =
      s"I'll have a ${isDouble.b2s} $brand, $mode, ${glass.g2s}."
  }

  abstract class TRUE
  abstract class FALSE

  class ScotchBuilder[HB, HM, HD](
    val theBrand: Option[String],
    val theMode: Option[Preparation],
    val theDoubleStatus: Option[Boolean],
    val theGlass: Option[Glass]) {

    def withBrand(b: String)     = new ScotchBuilder[TRUE, HM, HD](Some(b), theMode, theDoubleStatus, theGlass)
    def withMode(p: Preparation) = new ScotchBuilder[HB, TRUE, HD](theBrand, Some(p), theDoubleStatus, theGlass)
    def isDouble(b: Boolean)     = new ScotchBuilder[HB, HM, TRUE](theBrand, theMode, Some(b), theGlass)
    def withGlass(g: Glass)      = new ScotchBuilder[HB, HM, HD](theBrand, theMode, theDoubleStatus, Some(g))
  }

  implicit def enableBuild(builder: ScotchBuilder[TRUE, TRUE, TRUE]) = new {
    def build() = OrderOfScotch(builder.theBrand.get, builder.theMode.get, builder.theDoubleStatus.get, builder.theGlass)
  }

  def builder = new ScotchBuilder[FALSE, FALSE, FALSE](None, None, None, None)

  val order = builder withBrand "Lagavulin" withMode Neat isDouble true withGlass Tall build()
  // Does not compile:
  // val order = builder withBrand "hi" isDouble false withGlass Tall build()

  println(order)
  // Output:
  // I'll have a double Lagavulin, neat, in a tall glass.
}
