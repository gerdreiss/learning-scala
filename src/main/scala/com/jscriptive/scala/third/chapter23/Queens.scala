package com.jscriptive.scala.third.chapter23

object Queens {

  case class Pos(row: Int, col: Int)

  type Row = List[Pos]
  type Rows = List[Row]

  def inCheck(q1: Pos, q2: Pos): Boolean =
    q1.row == q2.row || q1.col == q2.col || // same row or column
      (q1.row - q2.row).abs == (q1.col - q2.col).abs // on diagonal

  def isSafe(queen: Pos, queens: Row): Boolean =
    queens forall (q => !inCheck(queen, q))

  def queens(n: Int): Rows = {
    def placeQueens(k: Int): Rows =
      if (k == 0) List(List())
      else
        for {
          queens <- placeQueens(k - 1)
          column <- 1 to n
          queen = Pos(k, column)
          if isSafe(queen, queens)
        } yield queen :: queens

    placeQueens(n)
  }

  def main(args: Array[String]): Unit = {
    println(queens(8))
  }
}
