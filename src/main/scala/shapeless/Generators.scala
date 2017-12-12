package shapeless

import scala.util.Random

object Generators extends App {

  trait Generator[A] {
    def generate: A
  }

  object Generator {
    def generate[A](implicit gen: Generator[A]): A = gen.generate
  }

  implicit def intGenerator: Generator[Int] = new Generator[Int] {
    override def generate: Int = Random.nextInt
  }

  implicit def doubleGenerator: Generator[Double] = new Generator[Double] {
    override def generate: Double = Random.nextDouble
  }

  implicit def booleanGenerator: Generator[Boolean] = new Generator[Boolean] {
    override def generate: Boolean = Random.nextBoolean
  }

  implicit def stringGenerator: Generator[String] = new Generator[String] {
    val loremWords: Array[String] =
      """
        | Lorem ipsum dolor sit amet, consectetur adipisicing elit,
        | sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        | Ut enim ad minim veniam, quis nostrud exercitation ullamco
        | laboris nisi ut aliquip ex ea commodo consequat.
        | Duis aute irure dolor in reprehenderit in voluptate velit
        | esse cillum dolore eu fugiat nulla pariatur.
        | Excepteur sint occaecat cupidatat non proident,
        | sunt in culpa qui officia deserunt mollit anim id est laborum.
      """.stripMargin.split("""\s|,|\.""")
    override def generate: String = Random.shuffle[String, List](loremWords.toList).take(5).mkString(" ")
  }

  implicit def genericToGenerator[T, L <: HList](implicit
    generic: Generic.Aux[T, L],
    lGen: Generator[L]): Generator[T] =
    new Generator[T] {
      override def generate: T = generic.from(lGen.generate)
    }

  implicit def hnilGenerator: Generator[HNil] = new Generator[HNil] {
    override def generate: HNil.type = HNil
  }

  implicit def hconsGenerator[H, T <: HList](implicit
    headGen: Generator[H],
    tailGen: Generator[T]): Generator[H :: T] =
    new Generator[H :: T] {
      override def generate: ::[H, T] = headGen.generate :: tailGen.generate
    }

  case class Sample(a: Int, b: Boolean, c: Double, d: String)
  val sample = Generator.generate[Sample]
  println(sample)
}
