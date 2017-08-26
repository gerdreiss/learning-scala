package third.chapter30

object Points {

  class Point(var x: Int, var y: Int) { // <-- mutable variables
    override def hashCode(): Int = (x, y).## // <-- that hashCode method tho!
    override def equals(o: scala.Any): Boolean = o match {
      case that: Point =>
          (that canEqual this) &&
          this.x == that.x &&
          this.y == that.y
      case _ => false
    }
    def canEqual(other: Any): Boolean = other.isInstanceOf[Point]
  }

  object Color extends Enumeration {
    val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
  }

  class ColoredPoint(x: Int, y: Int, val color: Color.Value) extends Point(x, y) {
    override def equals(o: Any): Boolean = o match {
      case that: ColoredPoint =>
          (that canEqual this) &&
          super.equals(that) &&
          this.color == that.color
      case _ => false
    }
    override def canEqual(other: Any): Boolean = other.isInstanceOf[ColoredPoint]
  }

  def main(args: Array[String]): Unit = {
    val p = new Point(1, 2)
    val coll = collection.mutable.HashSet(p)
    println("coll contains p: " + (coll contains p))
    coll.add(p)
    println("coll size: " + coll.size)
    p.x += 1
    println("coll still contains p: " + (coll contains p))
    println("coll iterator contains p: " + (coll.iterator contains p))
    coll.add(p)
    println("coll size: " + coll.size)
  }
}
