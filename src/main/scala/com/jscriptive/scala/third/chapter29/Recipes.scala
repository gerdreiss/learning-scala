package com.jscriptive.scala.third.chapter29

import com.jscriptive.scala.third.chapter29.Foods._

object Recipes {

  // Recipes
  class Recipe(val name: String, val ingredients: List[Food], val instructions: String) {
    override def toString: String = name
  }

  trait SimpleRecipes {

    object FruitSalad extends Recipe(
      "fruit salad",
      List(Apple, Orange, Pear, Cream, Sugar),
      "Stir it al together."
    )

    def allRecipes = List(FruitSalad)
  }

  trait StudentRecipes {

    object HeatItUp extends Recipe(
      "heat it up",
      List(FrozenFood),
      "Microwave the 'food' for 10 minutes."
    )

    def allRecipes = List(HeatItUp)
  }

}
