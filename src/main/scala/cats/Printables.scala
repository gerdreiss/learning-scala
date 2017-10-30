package cats

object Printables extends App {

  trait Printable[A] {
    def format(a: A): String
  }

  object PrintableInstances {
    implicit val stringPrintable = new Printable[String] {
      def format(value: String): String = value
    }
    implicit val intPrintable = new Printable[Int] {
      def format(value: Int): String = value.toString
    }
  }

  object Printable {
    def format[A](a: A)(implicit p: Printable[A]): String = p.format(a)
    def print[A](a: A)(implicit p: Printable[A]): Unit = println(format(a))
  }

  final case class Cat(name: String, age: Int, color: String)

  import PrintableInstances._

  implicit val catPrintable = new Printable[Cat] {
    def format(cat: Cat): String = {
      val name  = Printable.format(cat.name)
      val age   = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color cat."
    }
  }

  val cat = Cat("Garfield", 10, "ginger and black")
  Printable.print(cat)
}
