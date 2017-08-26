package third.chapter30

object Trees {

  trait Tree[+T] {
    def elem: T
    def left: Tree[T]
    def right: Tree[T]
  }

  object EmptyTree extends Tree[Nothing] {
    override def elem: Nothing = throw new NoSuchElementException("EmptyTree.elem")
    override def left: Tree[Nothing] = throw new NoSuchElementException("EmptyTree.left")
    override def right: Tree[Nothing] = throw new NoSuchElementException("EmptyTree.right")
  }

  class Branch[+T](val elem: T, val left: Tree[T], val right: Tree[T]) extends Tree[T] {
    override def hashCode(): Int = (elem, left, right).##
    override def equals(other: Any): Boolean = other match {
      case that: Branch[_] =>
        that.canEqual(this) &&
        this.elem == that.elem &&
        this.left == that.left &&
        this.right == that.right
      case _ => false
    }
    def canEqual(other: Any): Boolean = other match {
      case _: Branch[_] => true
      case _ => false
    }
    //def canEqual(other: Any): Boolean = other.isInstanceOf[Branch[_]]
  }

  def main(args: Array[String]): Unit = {
    val b1 = new Branch[String]("b1", EmptyTree, EmptyTree)
    val b2 = new Branch[String]("b2", EmptyTree, EmptyTree)
    println(b1 == b2)
    val b3 = new Branch[List[String]](Nil, EmptyTree, EmptyTree)
    val b4 = new Branch[List[Int]](Nil, EmptyTree, EmptyTree)
    println(b3 == b4)
  }
}
