package third.chapter29

import third.chapter29.Foods._
import third.chapter29.Recipes._

object Databases {

  // Databases
  abstract class Database extends FoodCategories {
    def allFoods: List[Food]
    def allRecipes: List[Recipe]
    def foodNamed(name: String): Option[Food] = allFoods.find(_.name == name)
  }

  object SimpleDatabase extends Database with SimpleFoods with SimpleRecipes {
    private val categories = List(
      FoodCategory("fruits", List(Apple, Orange, Pear)),
      FoodCategory("misc", List(Cream, Sugar))
    )

    override def allCategories: List[FoodCategory] = categories
  }

  object StudentDatabase extends Database with StudentFoods with StudentRecipes {
    val categories = List(
      FoodCategory("edible", List(FrozenFood))
    )

    override def allCategories: List[FoodCategory] = categories
  }

}
