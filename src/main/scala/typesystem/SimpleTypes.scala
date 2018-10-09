package typesystem

object SimpleTypes extends App {

  abstract class Food {
    val name: String
  }

  abstract class Fruit extends Food

  case class Banana(name: String) extends Fruit
  case class Apple(name: String) extends Fruit

  abstract class Cereal extends Food

  case class Granola(name: String) extends Cereal
  case class Muesli(name: String) extends Cereal

  val fuji = Apple("Fuji")
  val alpen = Muesli("Alpen")

  def eat(f: Food): String = s"${f.name} eaten"

  println(eat(fuji))

  // standard bowl of food
  case class Bowl(food: Food) {
    override def toString: String = s"A bowl of yummy ${food.name}"
    def contents: Food = food
  }

  // Type parameter with upper bound (<: Food)
  case class BowlF[F <: Food](food: F) {
    override def toString: String = s"A bowl of yummy ${food.name}"
    def contents: Food = food
  }

  val fruitBowl = Bowl(fuji)
  val cerealBowl = Bowl(alpen)
  val fruitBowlF = BowlF(fuji)
  val cerealBowlF = BowlF(alpen)

  println(fruitBowl)
  println(cerealBowl)
  println(fruitBowlF)
  println(cerealBowlF)

}
