package hackerrank.algorithms.impl

object Encryption {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    val sentence = sc.next()
    val cols = math.sqrt(sentence.length).ceil.toInt
    val result = sentence.grouped(cols)
      .map(s => s.toCharArray)
      .toArray.transpose
      .map(_.mkString)
      .mkString(" ")
    println(result)
  }
}
