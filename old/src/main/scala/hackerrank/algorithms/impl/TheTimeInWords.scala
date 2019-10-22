package hackerrank.algorithms.impl

object TheTimeInWords {

  val hours: Map[Int, String] = (1 to 12).zip(List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve")).toMap
  val mUnits: Map[Int, String] = (1 to 9).zip(List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")).toMap
  val mTeens: Map[Int, String] = (10 to 19).zip(List("ten", "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", "eighteen", "nineteen")).toMap

  def convertMinutes(m: Int): String = {
    if (m == 1) s"${mUnits(m)} minute"
    else if (m < 10) s"${mUnits(m)} minutes"
    else if (m == 15) "quarter"
    else if (m < 20) s"${mTeens(m)} minutes"
    else if (m == 20) "twenty minutes"
    else if (m < 30) s"twenty ${mUnits(m % 20)} minutes"
    else "half"
  }

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val h = sc.nextInt
    val m = sc.nextInt
    if (m == 0) println(s"${hours(h)} o' clock")
    else {
      val (hh, mm, p) = if (m > 30) (if (h == 12) 1 else h + 1, convertMinutes(60 - m), "to") else (h, convertMinutes(m), "past")
      println(s"$mm $p ${hours(hh)}")
    }
  }
}
