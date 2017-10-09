package fp.red.chapter4

object Exceptions {

  //noinspection ScalaUnreachableCode
  def failingFn1(i: Int): Int = {
    lazy val y: Int = throw new Exception("fail!")
    try {
      val x = 42 + i
      x + y
    }
    catch {
      case _: Exception => 43
    }
  }

  def failingFn2(i: Int): Int = {
    try {
      val x = 42 + i
      x + ((throw new Exception("fail!")): Int)
    }
    catch {
      case _: Exception => 43
    }
  }

  def main(args: Array[String]): Unit = {
    println(failingFn1(1))
    println(failingFn2(1))
  }
}
