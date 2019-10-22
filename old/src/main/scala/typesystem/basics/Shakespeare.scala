package typesystem.basics

object Shakespeare extends App {

  val or = "or"
  val to = "or"

  object To {
    def be(o: or.type) = this
    def not(t: to.type) = this
    def be = "That is the question"
  }

  println (To be or not to be)

}
