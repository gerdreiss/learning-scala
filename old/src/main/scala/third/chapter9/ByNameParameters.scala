package third.chapter9

object ByNameParameters {

  var assertionsEnabled = true

  def myAssert(predicate: () => Boolean) = {
    println("myAssert: evaluate the predicate 2")
    if (assertionsEnabled && !predicate()) {
      throw new AssertionError
    }
  }

  def byValueAssert(predicate: Boolean) = {
    println("byValueAssert: evaluate the predicate 2")
    if (assertionsEnabled && !predicate) {
      throw new AssertionError
    }
  }

  def byNameAssert(predicate: => Boolean) = {
    println("byNameAssert: evaluate the predicate 2")
    if (assertionsEnabled && !predicate) {
      throw new AssertionError
    }
  }

  def main(args: Array[String]): Unit = {
    myAssert(() => {
      println("myAssert: evaluate the predicate 1")
      3 > 2
    })
    byValueAssert({
      println("byValueAssert: evaluate the predicate 1")
      3 > 2
    })
    byNameAssert({
      println("byNameAssert: evaluate the predicate 1")
      3 > 1
    })
  }
}
