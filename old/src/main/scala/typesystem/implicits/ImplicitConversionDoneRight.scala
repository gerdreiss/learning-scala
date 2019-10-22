package typesystem.implicits

object ImplicitConversionDoneRight extends App {

  // also sweet DSL
  implicit class TimesInt(val i: Int) extends AnyVal {
    def times(fn: => Unit): Unit =
      (0 until i) foreach {
        _ => fn
      }
  }

  5 times println("hello")

}
