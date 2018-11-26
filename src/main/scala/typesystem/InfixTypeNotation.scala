package typesystem

object InfixTypeNotation extends App {

  trait Hates[T1, T2] {
    def describe(t1: T1, t2: T2): String
  }

  class Loves[T1, T2] {
    def describe(t1: T1, t2: T2): String = s"$t1 loves $t2"
  }

  case class Person(name: String) {
    override def toString: String = name
  }

  case class PersonLoves(p1: Person, p2: Person) extends (Person Loves Person) with (Person Hates Person) {
    def sayIt: String = describe(p1, p2)
  }

  def sayItWithRoses(lovers: PersonLoves): String =
    s"Roses are red, violets are blue, I love you so much, I made you both stew, ${lovers.sayIt}"

  val mary = Person("Mary")
  val pete = Person("Pete")
  val lovers = PersonLoves(mary, pete)

  println(sayItWithRoses(lovers))

}
