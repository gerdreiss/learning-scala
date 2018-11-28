package typesystem.basics

object Enumerations extends App {

  object Color extends Enumeration {
    val Red, Green, Yellow, Blue = Value
  }

  object Size extends Enumeration {
    val S = Value(1, "Small")
    val M = Value(2, "Medium")
    val L = Value(3, "Large")
    val XL = Value(4, "Extra Large")
  }

  println(s"Red = ${Color.Blue}")
  println(s"Small = ${Size.L}")
  println(Color.Red == Size.S)
  println(Color.values)
  println(Size.apply(2))
  println(Size.withName("Small"))

  // Better, or at least more widely used
  sealed trait EnumTrait
  case object EnumVal1 extends EnumTrait
  case object EnumVal2 extends EnumTrait
  case object EnumVal3 extends EnumTrait
  case object EnumVal4 extends EnumTrait

}
