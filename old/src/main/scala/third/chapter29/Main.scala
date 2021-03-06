package third.chapter29

import third.chapter29.Browsers._
import third.chapter29.Databases._

object Main {

  // Main program
  def main(args: Array[String]): Unit = {
    SimpleDatabase.foodNamed("Apple").foreach { apple =>
      println(apple)
      SimpleBrowser.recipesUsing(apple).foreach(println)
    }
    SimpleDatabase.allCategories.foreach(
      SimpleBrowser.displayCategory
    )
    StudentDatabase.allCategories.foreach(
      StudentBrowser.displayCategory
    )
  }
}
