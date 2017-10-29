package fp.simplified

object TypeClasses101 extends App {

  sealed trait Animal
  final case class Dog(name: String) extends Animal
  final case class Cat(name: String) extends Animal
  final case class Bird(name: String) extends Animal

  trait BehavesLikeHuman[A] {
    def speak(a: A): Unit
  }

  object BehavesLikeHumanInstances {
    implicit val dogBehavesLikeHuman = new BehavesLikeHuman[Dog] {
      override def speak(dog: Dog): Unit = {
        println(s"Hi, I'm a Dog, my name is ${dog.name}")
      }
    }
    implicit val catBehavesLikeHuman = new BehavesLikeHuman[Cat] {
      override def speak(cat: Cat): Unit = {
        println(s"Hi, I'm a Cat, my name is ${cat.name}")
      }
    }
  }

  object BehavesLikeHumanSyntax {
    implicit class BehavesLikeHumanOps[A](value: A) {
      def speak(implicit behavesLikeHumanInstance: BehavesLikeHuman[A]): Unit = {
        behavesLikeHumanInstance.speak(value)
      }
    }
  }

  import BehavesLikeHumanInstances._
  import BehavesLikeHumanSyntax._

  val rover = Dog("Rover")
  val purrphy = Cat("Purrphy")

  rover.speak
  purrphy.speak
}
