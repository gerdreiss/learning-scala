package com.jscriptive.scala.advanced

import scala.reflect.runtime.{universe => ru}

object Reflections extends App {

  case class Person(name: String)
  case class Purchase(name: String, orderNumber: Int, var shipped: Boolean)

  def getTypeTag[T: ru.TypeTag](obj: T): ru.TypeTag[T] = ru.typeTag[T]

  val person = Person("Mike")

  val theType: ru.Type = getTypeTag(person).tpe
  val classPerson: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass
  println(theType)
  println(classPerson)

  val m: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
  val cm: ru.ClassMirror = m.reflectClass(classPerson)
  val ctor: ru.MethodSymbol = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod
  val ctorm: ru.MethodMirror = cm.reflectConstructor(ctor)
  println(cm)
  println(ctor)
  println(ctorm)

  val mp: Person = ctorm("Mike").asInstanceOf[Person]
  println(mp)

  val purchase = Purchase("Jeff Lebowski", 23819, shipped = false)
  val shippingTermSymb: ru.TermSymbol = ru.typeOf[Purchase].decl(ru.TermName("shipped")).asTerm
  val im: ru.InstanceMirror = m.reflect(purchase)
  val shippingFieldMirror = im.reflectField(shippingTermSymb)
  println(shippingFieldMirror.get)

}
