package com.jscriptive.scala.advanced

import scala.util.Try

final case class Lens[O, V](get: O => Option[V], set: (O, V) => Try[O]) {

  /** */
  def >>[W](inner: Lens[V, W]): Lens[O, W] =
    Lens[O, W](
      this.get(_).flatMap(inner.get),
      (o, w) => get(o).map(_o => inner.set(_o, w).flatMap(i => set(o, i))).get
    )

}

case class Inner2(name: String)
case class Inner1(name: String, inner: Inner2)
case class Outer(name: String, inner: Inner1)

object Lens extends App {

  val inner1: Lens[Outer, Inner1] = Lens(o => Option(o.inner), (o, i) => Try(o.copy(inner = i)))
  val inner2: Lens[Outer, Inner2] = inner1 >> Lens(o => Option(o.inner), (inner1, inner2) => Try(inner1.copy(inner = inner2)))

  val outer = Outer("outer", Inner1("inner1", Inner2("inner2")))

  println(inner2.get(outer))

}
