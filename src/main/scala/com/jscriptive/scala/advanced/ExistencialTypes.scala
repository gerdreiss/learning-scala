package com.jscriptive.scala.advanced

object ExistencialTypes extends App {

  def printContents(list: List[_]): Unit = list foreach println

  //                             List[_] can be written
  //                             as
  def printVerboseContents(list: List[T forSome {type T}]): Unit = list foreach println

  printContents(List(1, 2, "3", false))
  printVerboseContents(List(1, 2, "3", false))
}
