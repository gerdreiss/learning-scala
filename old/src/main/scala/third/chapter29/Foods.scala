package third.chapter29

object Foods {

  // Foods
  abstract class Food(val name: String) {
    override def toString: String = name
  }

  object Apple extends Food("Apple")
  object Orange extends Food("Orange")
  object Cream extends Food("Cream")
  object Sugar extends Food("Sugar")
  object Pear extends Food("Pear")

  object FrozenFood extends Food("FrozenFood")

  trait SimpleFoods {
    def allFoods = List(Apple, Orange, Pear, Cream, Sugar)
  }

  trait StudentFoods {
    def allFoods = List(FrozenFood)
  }

  trait FoodCategories {
    case class FoodCategory(name: String, foods: List[Food])
    def allCategories: List[FoodCategory]
  }
}
