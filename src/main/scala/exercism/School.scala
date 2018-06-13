package exercism

import scala.collection.mutable
import scala.language.postfixOps

class School {
  type DB = Map[Int, Seq[String]]

  private val _db = mutable.Map.empty[Int, Seq[String]]

  def add(name: String, g: Int): Unit =
    _db.update(g, grade(g) :+ name)

  def db: DB = _db toMap

  def grade(g: Int): Seq[String] =
    _db getOrElse(g, Seq.empty)

  def sorted: DB = _db.toSeq.sortBy(_._1) map {
    case (g, names) => (g, names.sorted)
  } toMap
}
