package cats

import cats.data.Reader
import cats.syntax.applicative._ // for `pure

object Readers extends App {

  type DbReader[A] = Reader[DB, A]

  case class DB(usernames: Map[Int, String], passwords: Map[String, String])

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(_.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(_.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): Reader[DB, Boolean] =
    for {
      username <- findUsername(userId)
      passwordOk <- username match {
        case Some(name) => checkPassword(name, password)
        case _ => false.pure[DbReader]
      }
    } yield passwordOk
    //findUsername(userId).flatMap {
    //  _.map { name =>
    //    checkPassword(name, password)
    //  }
    //    .getOrElse(false.pure[DbReader])
    //}



  val db = DB(
    Map(1 -> "dade", 2 -> "kate", 3 -> "margo"),
    Map("dade" -> "zerocool", "kate" -> "acidburn", "margo" -> "secret")
  )

  println(checkLogin(1, "zerocool").run(db))
  println(checkLogin(4, "davinci").run(db))

}
