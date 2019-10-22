package exercism

object ArmstrongNumbers {

  def isArmstrongNumber(number: Int): Boolean = {
    val digits = number.toString
    val digitize: Char => Int = _.asDigit
    val power: Int => Double = math.pow(_, digits.length)
    number == digits.map(digitize andThen power).sum
  }
}
