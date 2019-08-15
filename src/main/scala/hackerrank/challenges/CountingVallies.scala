package hackerrank.challenges

object CountingVallies extends App {
  // Complete the countingValleys function below.
  def countingValleys(n: Int, s: String): Int =
    s.map {
      case 'D' => -1
      case 'U' => 1
      case _ => 0
    }
      .foldLeft((0, 0)) {
        case ((valleys, acc), current) =>
          val newAcc = acc + current
          val newVallyes = if (current == 1 && newAcc == 0) valleys + 1 else valleys
          (newVallyes, newAcc)
      }
      ._1

  println(countingValleys(0, "UDDDUDUU"))
  println(countingValleys(0, "UDDDUDUUDUUUDDDDDUU"))
  println(countingValleys(0, "DDUU"))
}
