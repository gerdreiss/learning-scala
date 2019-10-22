package third.chapter23

object ForExpr {

  case class Person(name: String, isMale: Boolean, children: Person*)

  def main(args: Array[String]): Unit = {

    val lara = Person("Lara", isMale = false)
    val bob = Person("Bob", isMale = true)
    val julie = Person("Julie", false, lara, bob)
    val persons = List(lara, bob, julie)

    // Variant 1 = standard
    val momKidV1 = persons filter (p => !p.isMale) flatMap (p => p.children map (c => (p.name, c.name)))
    println(momKidV1)

    // Variant 2 = optimized, with withFilter instead of filter
    val momKidV2 = persons withFilter (p => !p.isMale) flatMap (p => p.children map (c => (p.name, c.name)))
    println(momKidV2)

    // Variant 3 = for loop instead of filter and map
    val momKidV3 = for (p <- persons; if !p.isMale; c <- p.children) yield (p.name, c.name)
    println(momKidV3)

    val names = for {
      p <- persons
      n = p.name
      if n startsWith "Ju"
    } yield n
    println(names)

    val pairs = for (x <- List(1, 2); y <- List("one", "two")) yield (x, y)
    println(pairs)
  }
}
