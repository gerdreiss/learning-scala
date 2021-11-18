import io.estatico.newtype.macros._
import eu.timepit.refined.types.string._
import eu.timepit.refined.api._
import eu.timepit.refined.collection._

object domain {

  type UserNameR = NonEmptyString
  object UserNameR extends RefinedTypeOps[UserNameR, String]

  type NameR = NonEmptyString
  object NameR extends RefinedTypeOps[NameR, String]

  type EmailR = String Refined Contains['@']
  object EmailR extends RefinedTypeOps[EmailR, String]

  @newtype case class UserName(value: UserNameR)
  @newtype case class Name(value: NameR)
  @newtype case class Email(value: EmailR)
  @newtype case class Brand(value: NonEmptyString)
  @newtype case class Category(value: NonEmptyString)

}
