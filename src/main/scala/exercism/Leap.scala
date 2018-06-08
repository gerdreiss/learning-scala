package exercism

/**
  * on every year that is evenly divisible by 4
  * except every year that is evenly divisible by 100
  * unless the year is also evenly divisible by 400
  */

object Leap {
  def leapYear(year: Int): Boolean = year % 400 == 0 || year % 100 != 0 && year % 4 == 0
}
