package com.jscriptive.scala.advanced

import scala.reflect.runtime.universe._

object Reflections extends App {

  case class Person(name: String)
  case class Purchase(name: String, orderNumber: Int, var shipped: Boolean)

  def getTypeTag[T: TypeTag](obj: T): TypeTag[T] = typeTag[T]

  val person = Person("Mike")

  val theType: Type = getTypeTag(person).tpe
  val classPerson: ClassSymbol = typeOf[Person].typeSymbol.asClass
  println(theType)
  println(classPerson)

  val m: Mirror = runtimeMirror(getClass.getClassLoader)
  val cm: ClassMirror = m.reflectClass(classPerson)
  val ctor: MethodSymbol = typeOf[Person].decl(termNames.CONSTRUCTOR).asMethod
  val ctorm: MethodMirror = cm.reflectConstructor(ctor)
  println(cm)
  println(ctor)
  println(ctorm)

  val mp: Person = ctorm("Mike").asInstanceOf[Person]
  println(mp)

  val purchase = Purchase("Jeff Lebowski", 23819, shipped = false)
  val shippingTermSymb: TermSymbol = typeOf[Purchase].decl(TermName("shipped")).asTerm
  val im: InstanceMirror = m.reflect(purchase)
  val shippingFieldMirror = im.reflectField(shippingTermSymb)
  println(shippingFieldMirror.get)

}
