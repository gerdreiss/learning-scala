package fp.chapter3

object Trees {

  sealed trait Tree[+A] {
    def map[B](f: A => B): Tree[B]
    def fold[B](f: A => B)(g: (B, B) => B): B
    def size: Int = fold(_ => 1)(1 + _ + _)
    def depth: Int = fold(_ => 0)((ld, rd) => 1 + (ld max rd))
  }

  case class Leaf[A](value: A) extends Tree[A] {
    def map[B](f: A => B): Leaf[B] = Leaf(f(value))
    def fold[B](f: A => B)(g: (B, B) => B): B = f(value)
    //def size: Int = 1
    //def depth: Int = 0
  }

  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
    def map[B](f: A => B): Branch[B] = Branch(left.map(f), right.map(f))
    def fold[B](f: A => B)(g: (B, B) => B): B = g(left.fold(f)(g), right.fold(f)(g))
    //def size: Int = 1 + left.size + right.size
    //def depth: Int = 1 + (left.depth max right.depth)
  }

  def main(args: Array[String]): Unit = {
    val t: Branch[Int] =
      Branch(
        Branch(
          Leaf(1),
          Branch[Int](
            Leaf(2),
            Leaf(4)
          )
        ),
        Leaf(3)
      )

    println(t.size)
    println(t.depth)
    println(t.map(v => "CHF %s,-".format(v.toString)))
  }
}