package fp

object Haskell extends App {

  val func1 = (_: Double) * 2.0
  val func2 = (_: Double) / 10.0
  val func3 = math.pow(_: Double, 4)

  val list = List(
    func1,
    func2,
    func3
  ) map (_(4.0))

  println(list.mkString("\n"))
  println(list.sum)

}
