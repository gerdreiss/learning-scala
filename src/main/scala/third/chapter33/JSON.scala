package third.chapter33

import java.io.FileReader

import scala.util.parsing.combinator._

class JSON extends JavaTokenParsers {

  def obj: Parser[Map[String, Any]] = "{" ~> repsep(member, ",") <~ "}" ^^ (Map() ++ _)

  def arr: Parser[List[Any]] = "[" ~> repsep(value, ",") <~ "]"

  def member: Parser[(String, Any)] = stringLiteral ~ ":" ~ value ^^ { case name ~ ":" ~ value => (name, value) }

  def value: Parser[Any] = (
    obj
      | arr
      | stringLiteral
      | floatingPointNumber ^^ (_.toDouble)
      | "null" ^^ (x => null)
      | "true" ^^ (x => true)
      | "false" ^^ (x => false)
    )

}

object ParseJSON extends JSON {
  def main(args: Array[String]): Unit = {
    val reader = new FileReader("/Users/gerdreiss/.atom/packages/language-haskell/coffeelint.json")
    println(parseAll(value, reader))
  }
}
