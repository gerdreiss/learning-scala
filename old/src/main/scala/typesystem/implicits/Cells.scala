package typesystem.implicits

object Cells extends App {

  case class Cell[T](item: T) {
    def *[U: Numeric](other: Cell[U])(implicit ev: T =:= U): Cell[U] = {
      val numClass = implicitly[Numeric[U]]
      Cell(numClass.times(this.item, other.item))
    }
  }

  val c1 = Cell(4.0)
  val c2 = Cell(2.0)

  println(c1 * c2)
}
