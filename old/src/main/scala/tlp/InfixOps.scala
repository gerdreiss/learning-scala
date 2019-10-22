package tlp

object Fooo {
  def bar(s: String): Unit = println(s)
}

trait Bar[A, B]
trait ::[A, B]

object InfixOps {

  Fooo.bar("hello") // standard
  Fooo bar "hello" // infix

  type Test1 = Bar[Int, String] // standard
  type Test2 = Int Bar String // infix

  type Test3 = ::[Int, String] // standard
  type Test4 = Int :: String // infix
}
