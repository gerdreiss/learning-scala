package fp.simplified

object MyBinder extends App {

  case class Debuggable[A](value: A, log: List[String]) {
    def map[B](f: A => B): Debuggable[B] = {
      Debuggable(f(value), log)
    }
    def flatMap[B](f: A => Debuggable[B]): Debuggable[B] = {
      val newValue = f(value)
      Debuggable(newValue.value, log ::: newValue.log)
    }
    override def toString: String = s"value = $value; log = { ${log.mkString} }"
  }

  def f(a: Int) = Debuggable(a + 1, List(s"f() = ${a + 1}; "))
  def g(a: Int) = Debuggable(a + 2, List(s"g() = ${a + 2}; "))
  def h(a: Int) = Debuggable(a + 3, List(s"h() = ${a + 3}; "))

  val finalResult = for {
    fres <- f(100)
    gres <- g(fres)
    hres <- h(gres)
  } yield hres

  println(finalResult)
}
