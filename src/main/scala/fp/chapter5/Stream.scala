package fp.chapter5

import fp.chapter5.Stream._

sealed trait Stream[+A] {
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, _) => Some(h())
  }

  /**
    * Natural recursive solution
    */
  def toListRec: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toListRec
  }

  /**
    * The above solution will stack overflow for large streams, since it's
    * ot tail-recursive. Here is a tail-recursive implementation. At each
    * tep we cons onto the front of the `acc` list, which will result in the
    * severse of the stream. Then at the end we reverse the result to get the
    * correct order again.
    */
  def toList: List[A] = {
    @annotation.tailrec
    def loop(s: Stream[A], acc: List[A]): List[A] = s match {
      case Cons(h, t) => loop(t(), h() :: acc)
      case _ => acc
    }
    loop(this, List()).reverse
  }

  /**
    * In order to avoid the `reverse` at the end, we could write it using a
    * mutable list buffer and an explicit loop instead. Note that the mutable
    * list buffer never escapes our `toList` method, so this function is
    * still _pure_.
    */
  def toListFast: List[A] = {
    val buf = new collection.mutable.ListBuffer[A]
    @annotation.tailrec
    def loop(s: Stream[A]): List[A] = s match {
      case Cons(h, t) =>
        buf += h()
        loop(t())
      case _ => buf.toList
    }
    loop(this)
  }

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if n > 1 => cons(h(), t().take(n - 1))
    case Cons(h, _) if n == 1 => cons(h(), empty)
    case _ => empty
  }

  @annotation.tailrec
  final def drop(n: Int): Stream[A] = this match {
    case Cons(_, t) if n > 0 => t().drop(n - 1)
    case _ => this
  }

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if p(h()) => cons(h(), t() takeWhile p)
    case _ => empty
  }

  def exists(p: A => Boolean): Boolean = this match {
    case Cons(h, t) => p(h()) || t().exists(p)
    case _ => false
  }

  def exists2(p: A => Boolean): Boolean = {
    foldRight(false)((a, b) => p(a) || b)
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def forAll(p: A => Boolean): Boolean = {
    foldRight(true)((a, b) => p(a) && b)
  }

  def takeWhile_folded(p: A => Boolean): Stream[A] = {
    foldRight(empty[A])((h, t) => if (p(h)) cons(h, t) else empty)
  }

  def headOption_folded: Option[A] =
    foldRight(None: Option[A])((h, _) => Some(h))

  def map_folded[B](f: A => B): Stream[B] =
    foldRight(empty[B])((h, t) => cons(f(h), t))

  def filter_folded(f: A => Boolean): Stream[A] =
    foldRight(empty[A])((h, t) => if (f(h)) cons(h, t) else t)

  def append_folded[B >: A](s: => Stream[B]): Stream[B] =
    foldRight(s)((h, t) => cons(h, t))

  def flatMap_folded[B](f: A => Stream[B]): Stream[B] =
    foldRight(empty[B])((h, t) => f(h) append_folded t)

  def constant[A](a: A): Stream[A] =
    cons(a, constant(a))

  def from(n: Int): Stream[Int] =
    cons(n, from(n + 1))

  def fibs(): Stream[Int] = {
    def loop(prev: Int, curr: Int): Stream[Int] =
      cons(prev, loop(curr, prev + curr))
    loop(0, 1)
  }
}

case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
}
