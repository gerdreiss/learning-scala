package neophytesguide

trait User {
  def name: String
  def score: Int
}

class FreeUser(val name: String, val score: Int, val upgradeProbability: Double) extends User
class PremiumUser(val name: String, val score: Int) extends User

object FreeUser {
  def unapply(user: FreeUser): Option[(String, Int, Double)] =
    Some((user.name, user.score, user.upgradeProbability))
}

object PremiumUser {
  def unapply(user: PremiumUser): Option[(String, Int)] =
    Some((user.name, user.score))
}

object premiumCandidate {
  def unapply(user: FreeUser): Boolean = user.upgradeProbability > 0.75
}

object GivenNames {
  def unapplySeq(name: String): Option[Seq[String]] = {
    val names = name.trim.split(" ")
    if (names.forall(_.isEmpty)) None else Some(names)
  }
}

object Extractors extends App {

  val user: User = new FreeUser("Daniel", 2500, 0.8d)
  user match {
    case FreeUser(n, s, p)           => println(s"Unapplied user: $n, $s, $p")
    case freeUser@premiumCandidate() => println(s"Free user: $freeUser")
    case _                           => println(s"Random user: $user")
  }

  def greetWithFirstName (name: String) = name match {
    case GivenNames (firstName, _*) => "Good morning, " + firstName + "!"
    case _                          => "Welcome! Please make sure to fill in your name!"
  }

  println(greetWithFirstName(""))
  println(greetWithFirstName("Igor"))
  println(greetWithFirstName("Igor Yevgenievich Kalashnikov"))

}