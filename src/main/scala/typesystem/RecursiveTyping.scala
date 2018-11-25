package typesystem

object RecursiveTyping extends App {

  trait CompareT[T] {
    def >(other: T): Boolean
    def <(other: T): Boolean
  }

  def genInsert[T <: CompareT[T]](item: T, rest: List[T]): List[T] = rest match {
    case Nil => List(item)
    case head :: _ if item < head => item :: rest
    case head :: tail => head :: genInsert(item, tail)
  }

  def genSort[T <: CompareT[T]](xs: List[T]): List[T] = xs match {
    case Nil => Nil
    case head :: tail => genInsert(head, genSort(tail))
  }

  case class Distance(meters: Int) extends CompareT[Distance] {
    override def >(other: Distance): Boolean = this.meters > other.meters
    override def <(other: Distance): Boolean = this.meters < other.meters
  }
  case class Person(firstName: String, lastName: String) extends CompareT[Person ]{
    override def >(other: Person): Boolean = lastName>other.lastName||firstName>other.firstName
    override def <(other: Person): Boolean = lastName<other.lastName||firstName<other.firstName
  }

  val distances = List(Distance(100), Distance(1000), Distance(20), Distance(1))
  val persons = List(Person("George", "Bush"), Person("Hillary", "Clinton"), Person("Michael", "Jackson"))

  println(genSort(distances))
  println(genSort(persons))
}
