package scalaz.day0

/**
 * Created by grei on 25.08.2017
 */
object Polymorphism {

  trait Plus[A] {
    def plus(a1: A, a2: A): A
  }

  def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)

}