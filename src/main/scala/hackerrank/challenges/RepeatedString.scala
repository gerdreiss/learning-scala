package hackerrank.challenges

object RepeatedString extends App {

  def repeatedString(s: String, n: Long): Long = {
    val mod = n % s.length
    val mult = n / s.length
    s.take(mod.toInt).count(_ == 'a') + s.count(_ == 'a') * mult
  }

  println(repeatedString("aba", 10L))
  println(repeatedString("a", 10000000L))
}
