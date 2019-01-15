package starmind

object Main extends App {
  val msg = Vector(
    0x4A, 0x6F, 0x69, 0x6E, 0x20, 0x2605, 0x6D, 0x69, 0x6E, 0x64
  )

  def convert[T](seq: Seq[T], f: T => Char): String = {
    (seq map f) mkString
  }

  println(convert[Int](msg, _.toChar))
}
