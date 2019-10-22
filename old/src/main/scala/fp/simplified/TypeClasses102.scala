package fp.simplified

object TypeClasses102 extends App {

  trait ToString[A] {
    def asString(a: A): String
  }

  implicit val pizzaAsString = new ToString[Pizza] {
    override def asString(pizza: Pizza): String =
      s"""
         | Pizza (${pizza.crustSize}, ${pizza.crustType}), toppings = ${pizza.toppings.mkString(", ")}
         """.stripMargin
  }

  implicit class ToStringOps[A](value: A) {
    def asString(implicit toStringInstance: ToString[A]): String = toStringInstance.asString(value)
  }

  val p = Pizza(LargeCrustSize, ThinCrustType, Seq(Cheese, Pepperoni, Sausage))

  println(p.asString)
}
