package com.jscriptive.scala.advanced

import scala.reflect.ClassTag

object TypeErasureAndTypeTags extends App {

  // This does not compile without import scala.reflect.ClassTag
  // and the [T: ClassTag] type declaration (or the implicit parameter tag)
  def createArray[T: ClassTag](length: Int, element: T) /*(implicit tag: ClassTag[T])*/ =
    new Array[T](length)

  println(createArray(5, 1.0) mkString " ")
}
