package com.jscriptive.scala.advanced

object Implicits extends App {

  //def sayHello1(implicit name: String): String = s"Hello $name"
  //implicit val name = "Joe"
  //println(sayHello1)

  class Person1(val name: String)

  object Person1 {
    implicit val person: Person1 = new Person1("User")
    implicit val maybePerson: Option[Person1] = Some(new Person1("Some User"))
  }

  def sayHello2(implicit person: Person1): String = s"Hello ${person.name}"
  println(sayHello2)

  def sayHello3(implicit maybePerson: Option[Person1]): String = s"Hello ${maybePerson.map(_.name).getOrElse("Wha?")}"
  println(sayHello3)


  case class Person2(name: String) {
    def greet: String = s"Hello! I'm $name"
  }

  object Person2 {
    implicit def stringToPerson(str: String): Person2 = Person2(str)
  }

  import Person2.stringToPerson

  println("Joe".greet)

}
