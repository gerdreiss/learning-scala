package shoppingcart

import io.estatico.newtype.macros._
import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.string._
import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString

object Domain {

  type TUsername = String Refined MatchesRegex[W.`"""^[a-zA-Z0-9_]{6,}$"""`.T]
  type TEmail    = String Refined MatchesRegex[W.`"""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"""`.T]

  @newtype case class Username(value: TUsername)
  @newtype case class Email(value: TEmail)

  // usage:
  //import io.estatico.newtype.ops._
  //"name@mail.com".coerce[Email]

  //def lookup(username: NonEmptyString): F[Option[User]]

}
