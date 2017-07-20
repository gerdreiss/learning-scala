package com.jscriptive.scala.advanced

final case class Lens[O, V](get: O => Option[V], set: (O, V) => Try[O]) {

  /** */
  def >>[W](inner: Lens[V, W]): Lens[O, W] =
    Lens[O, W](
      this.get(_).flatMap(inner.get(_)),
      (o, w) => inner.set(get(o), w).flatMap(i => set(o, i))
    )

}

case class Inner2(val s: String)
case class Inner1(val s: String, val inner: Inner2)
case class Outer(val s: String, val inner: Inner1)

object Lens {

  val inner1: Lens[Outer, Inner1] = Lens(o => Option(o.inner), (o, i) => Try(o.copy(inner = i)))
  val inner2: Lens[Outer, Inner2] = inner1 >> Lens(inner1 = Option(i1.inner), (inner1, inner2) => Try(inner1.copy(inner2 = inner2)))

}
