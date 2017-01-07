package com.jscriptive.scala.third.chapter29

import com.jscriptive.scala.third.chapter29.Databases._
import com.jscriptive.scala.third.chapter29.Foods._
import com.jscriptive.scala.third.chapter29.Recipes._

object Browsers {

  // Browsers
  abstract class Browser {
    val database: Database

    def recipesUsing(food: Food): List[Recipe] =
      database.allRecipes.filter(recipe =>
        recipe.ingredients.contains(food))

    def displayCategory(category: database.FoodCategory): Unit = {
      println(category)
    }
  }

  object SimpleBrowser extends Browser {
    val database = SimpleDatabase
  }

  object StudentBrowser extends Browser {
    val database = StudentDatabase
  }

}
