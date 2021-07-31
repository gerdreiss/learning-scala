@main def hello( /*name: String, age: Int*/ ): Unit =
  //println("-" * 50)
  //println(s"Hello, $name ($age)!")
  println("-" * 50)
  usage()
  println("-" * 50)
  println("factorial.by_product(5)           = " + factorial.by_product(5))
  println("factorial.by_foldLeft(5)          = " + factorial.by_foldLeft(5))
  println("factorial.by_while_do(5)          = " + factorial.by_while_do(5))
  println("factorial.by_recursion(5)         = " + factorial.by_recursion(5))
  println("factorial.by_recursion_tailrec(5) = " + factorial.by_recursion_tailrec(5))
  println("-" * 50)
  println("Loop A: " + loopA(List(1, 1, 2, 3, 5, 8)))
  println("Loop B: " + loopB(List(1, 1, 2, 3, 5, 8)))
  println("Loop C: " + loopC(List(1, 1, 2, 3, 5, 8)))
  println("Loop D: " + loopD(List(1, 1, 2, 3, 5, 8)))

def msg = "I was compiled by Scala 3. :)"
