package hackerrank.algorithms.warmup

object TimeConversion {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val pattern = "^(1[012]|0[1-9]):([0-5][0-9]):([0-5][0-9])(AM|PM)$".r
    val pattern(hh, mm, ss, tod) = sc.next()
    val result = tod match {
      case "PM" if hh == "12" => s"12:$mm:$ss"
      case "PM" => f"${(hh.toInt + 12) % 24}%02d:$mm:$ss"
      case "AM" if hh == "12" => s"00:$mm:$ss"
      case _    => s"$hh:$mm:$ss"
    }
    println(result)
  }
}
