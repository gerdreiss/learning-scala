package com.jscriptive.scala.effective

object Collections {

  def main(args: Array[String]): Unit = {

    val nums = List(1, 2, 3, 5, 6, 7, 8, 3, 2, 4, 3, 1)

    println(nums.sorted)
    println(nums.distinct.sorted)
    println(nums.sum)
    println(nums.product)
    println(nums.sorted.mkString("<----%%_", "_%%_", "_%%---->"))

    val nums2 = List(4, 5, 6, 7, 8, 9)
    println(nums2.sorted)

    println(nums intersect nums2)
    println(nums diff nums2)

    val matrix = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    println(matrix.transpose)
  }

}
