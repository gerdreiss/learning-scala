package fp

object HaskellInScala extends App {

  val func1 = (_: Double) * 2.0
  val func2 = (_: Double) / 10.0
  val func3 = math.pow(_: Double, 4)

  val list = List(
    func1,
    func2,
    func3
  ) map (_ (4.0))

  println(list.mkString("\n"))
  println(list.sum)


  //  coins :: (Ord a, Num a) => [a]
  //  coins = [2, 3, 5, 10]
  def coins = Seq(2, 3, 5, 10)

  //  change :: (Ord a, Num a) => a -> [[a]]
  //  change 0   = [[]]
  //  change sum = [c : r | c <- coins, c <= sum, r <- change (sum - c)]
  def change(sum: Int): Seq[Seq[Int]] = sum match {
    case 0 => Seq(Seq.empty)
    case _ => for {
      c <- coins
      if c <= sum
      r <- change(sum - c)
    } yield c +: r
  }

  println(change(7))
  println(change(17))
}
