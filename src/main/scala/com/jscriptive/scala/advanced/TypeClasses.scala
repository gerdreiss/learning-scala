package com.jscriptive.scala.advanced

object TypeClasses extends App {

  trait InfoPrinter[T] {
    def toInfo(value: T): String
  }

  object DefaultInfoPrinters {

    implicit val stringPrinter = new InfoPrinter[String] {
      override def toInfo(value: String): String = s"[String] $value"
    }

    implicit val intPrinter = new InfoPrinter[Int] {
      override def toInfo(value: Int): String = s"[Int] $value"
    }

    implicit val boolPrinter = new InfoPrinter[Boolean] {
      override def toInfo(value: Boolean): String = s"[Boolean] $value"
    }
  }

  object PrintInfoSyntax {

    implicit class PrintInfoOps[T](value: T) {
      def printInfo()(implicit printer: InfoPrinter[T]): Unit = {
        println(printer.toInfo(value))
      }
    }
  }

  import DefaultInfoPrinters._
  import PrintInfoSyntax._

  val number = 42
  number.printInfo()

  val b = true
  b.printInfo()

  "Is this going to work?".printInfo()

  case class User(name: String, age: Int)
  object User {
    implicit val userPrinter = new InfoPrinter[User] {
      override def toInfo(value: User): String = s"[User] (${value.name}, ${value.age})"
    }
  }

  User("me", 42).printInfo()
}
