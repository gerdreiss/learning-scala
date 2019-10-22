package hackerrank.algorithms.search

object Pairs {

  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val n = sc.nextInt
    val k = sc.nextInt
    val ts: IndexedSeq[(Int, Int)] = (1 to n).map(_ => sc.nextInt).sorted.zipWithIndex
    def findK(idx: Int, num: Int): Boolean = {
      if (idx == ts.size) false
      else {
        val d = ts(idx)._1 - num
        if (d < k) findK(idx + 1, num)
        else d == k
      }
    }
    val pairs = for {
      (num, idx) <- ts
      if findK(idx + 1, num)
    } yield 1
    println(pairs.sum)
  }
}
