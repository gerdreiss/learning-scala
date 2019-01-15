object RoyalFlush extends App {

  val cards: Seq[String] = for {
    rang <- Seq("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    color <- Seq("H", "S", "D", "C")
  } yield rang + color

  cards.sliding(1).foreach(println)

  // TODO [grei]: implement!!
}
