package com.jscriptive.scala.third.chapter29

import com.jscriptive.scala.third.chapter29.Browsers.Browser
import com.jscriptive.scala.third.chapter29.Databases.{Database, SimpleDatabase, StudentDatabase}

object GotApples extends App {
  val db: Database =
    if (args.isEmpty)
      SimpleDatabase
    else if (args(0) == "student")
      StudentDatabase
    else
      SimpleDatabase

  object browser extends Browser {
    val database: db.type = db
  }

  val apple = SimpleDatabase.foodNamed("Apple").get

  browser.recipesUsing(apple).foreach(println)
}
