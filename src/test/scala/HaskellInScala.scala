import org.scalatest.{FunSuite, Matchers}

import scala.collection.immutable

/**
  * Trying to implement things the Haskell way
  */
class HaskellInScala extends FunSuite with Matchers {

  val show = (x: Int) => x.toString
  val digitToInt = (s: String) => s.map(_.asDigit)
  val sum = (xs: Seq[Int]) => xs.sum

  val digitSum: Int => Int =
    sum compose digitToInt compose show

  test(
    """Haskell:
      | digitSum :: Int -> Int
      | digitSum = sum . map digitToInt . show
      |""".stripMargin) {

    digitSum(0) should be(0)
    digitSum(49999) should be(40)
    digitSum(599999) should be(50)

  }

  val firstTo: Int => Option[Int] =
    (n: Int) => Stream.from(0) find (digitSum(_) == n)

  test(
    """Haskell:
      | firstTo :: Int -> Maybe Int
      | firstTo n = find (\x -> digitSum x == n) [0..]
    """.stripMargin
  ) {

    firstTo(0) should be(Some(0))
    firstTo(40) should be(Some(49999))
    firstTo(50) should be(Some(599999))
  }
}
