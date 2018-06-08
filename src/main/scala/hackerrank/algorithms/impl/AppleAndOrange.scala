package hackerrank.algorithms.impl

object AppleAndOrange {
  def main(args: Array[String]): Unit = {
    val sc = new java.util.Scanner(System.in)
    val st = sc.nextInt to sc.nextInt
    val a = sc.nextInt
    val b = sc.nextInt
    val m = sc.nextInt
    val n = sc.nextInt
    val apples = (1 to m).map(_ => sc.nextInt).count(apple => st.contains(apple + a))
    val oranges = (1 to n).map(_ => sc.nextInt).count(orange => st.contains(orange + b))
    println(apples)
    println(oranges)
  }
}
