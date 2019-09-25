package hackerrank.challenges

object JumpingOnTheClouds extends App {

  // Complete the jumpingOnClouds function below.
  def jumpingOnClouds(c: Array[Int]): Int = {
    if (c.length < 3) c.length - 1
    else 1 + jumpingOnClouds(c.drop(if (c(2) == 0) 2 else 1))
  }

  println(jumpingOnClouds(Array(0, 0, 1, 0, 0, 1, 0, 0, 0)))
}
