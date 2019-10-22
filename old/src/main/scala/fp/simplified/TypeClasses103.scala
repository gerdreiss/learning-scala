package fp.simplified

import cats.Show
import cats.syntax.show._

object TypeClasses103 extends App {

  implicit val pizzaShow = Show.show[Pizza] { pizza =>
    s"""|Pizza (${pizza.crustSize}, ${pizza.crustType}), toppings = ${pizza.toppings.mkString(", ")}""".stripMargin
  }

  val pizza = Pizza(LargeCrustSize, ThinCrustType, Seq(Cheese, Pepperoni, Sausage))

  println(pizza.show)
}
