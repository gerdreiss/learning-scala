package hackerrank.algorithms.impl

object GradingStudents {

  def main(args: Array[String]) {
    val sc = new java.util.Scanner(System.in)
    (0 until sc.nextInt)
      .map(_ => sc.nextInt)
      .map { grade =>
        lazy val nextMultipleOf5: Int = ((grade / 5) + 1) * 5
        if (grade >= 38 && nextMultipleOf5 - grade < 3) nextMultipleOf5
        else grade
      }
      .foreach(println)
  }
}
