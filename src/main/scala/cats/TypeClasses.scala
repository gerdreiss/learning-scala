package cats


// Define a very simple JSON AST
sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
case object JsNull extends Json

// The "serialize to JSON" behaviour is encoded in this trait
trait JsonWriter[A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)
final case class Bank(iban: String, bic: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String] {
      def write(value: String): Json =
        JsString(value)
    }

  implicit val personWriter: JsonWriter[Person] =
    new JsonWriter[Person] {
      def write(value: Person): Json =
        JsObject(Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        ))
    }
  implicit val bankWriter: JsonWriter[Bank] =
    new JsonWriter[Bank] {
      def write(value: Bank): Json =
        JsObject(Map(
          "iban" -> JsString(value.iban),
          "bic" -> JsString(value.bic)
        ))
    }
}

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}

object TypeClasses extends App {

  import JsonWriterInstances._
  import JsonSyntax._

  implicitly[JsonWriter[String]]

  println(Person("Dave", "dave@example.com").toJson)
  println(Bank("ES90239029304283049", "BJSLKDF").toJson)
}