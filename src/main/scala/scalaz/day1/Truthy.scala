package scalaz.day1

import scala.language.implicitConversions

object Truthy extends App{

  trait CanTruthy[A] { self =>
    /** @return true, if `a` is truthy. */
    def truthys(a: A): Boolean
  }

  object CanTruthy {
    def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev
    def truthys[A](f: A => Boolean): CanTruthy[A] = (a: A) => f(a)
  }

  trait CanTruthyOps[A] {
    def self: A
    implicit def F: CanTruthy[A]
    final def truthy: Boolean = F.truthys(self)
  }

  object ToCanTruthyOps {
    implicit def toCanTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) =
      new CanTruthyOps[A] {
        def self = v
        implicit def F: CanTruthy[A] = ev
      }
  }

  import ToCanTruthyOps._

  implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys(_ > 0)

  println(10.truthy)

  implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys(_.nonEmpty)

  println(List("foo").truthy)
  println(List().truthy)

  implicit def nilCanTruthy: CanTruthy[Nil.type] = CanTruthy.truthys(_ => false)

  println(Nil.truthy)

  implicit val booleanCanTruthy: CanTruthy[Boolean] = CanTruthy.truthys(identity)

  println(false.truthy)

  def truthyIf[A: CanTruthy, B, C](cond: A)(ifyes: => B)(ifno: => C) =
    if (cond.truthy) ifyes
    else ifno

  println(truthyIf (Nil) {"YEAH!"} {"NO!"})
  println(truthyIf (2 :: 3 :: 4 :: Nil) {"YEAH!"} {"NO!"})

}