package third.chapter19

object Queues2 {

  trait Queue[T] {
    def head: T
    def tail: Queue[T]
    def enqueue(x: T): Queue[T]
  }

  object Queue {

    def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)

    private class QueueImpl[T](private val leading: List[T],
                               private val trailing: List[T]) extends Queue[T] {

      def mirror: QueueImpl[T] =
        if (leading.isEmpty) new QueueImpl[T](trailing.reverse, Nil)
        else this

      override def head: T = mirror.leading.head

      override def tail: Queue[T] = {
        val q = mirror
        new QueueImpl[T](q.leading.tail, q.trailing)
      }

      override def enqueue(x: T): Queue[T] = new QueueImpl[T](leading, x :: trailing)
    }

  }

  def main(args: Array[String]): Unit = {
    val q1 = Queue(1, 2, 3)
    val q2 = q1 enqueue 4 enqueue 5
    println("q1 == q2 ? -> " + (q1 == q2))
  }

}
