package codefights

object MakeArrayConsecutive2 extends App {

  def makeArrayConsecutive2(statues: Array[Int]): Int = statues match {
    case Array() | Array(_) => 0
    case _ => statues.sorted.sliding(2)
      .map {
        case Array(x, y) => y - x - 1
      }
      .sum
  }

  println(makeArrayConsecutive2(Array(6, 2, 3, 8)))
  println(makeArrayConsecutive2(Array(0, 3)))
  println(makeArrayConsecutive2(Array(5, 4, 6)))
  println(makeArrayConsecutive2(Array(6, 3)))
  println(makeArrayConsecutive2(Array(1)))

}
