package shoppingcart

import java.time.LocalDate
import java.util.UUID

import io.estatico.newtype.macros._
import eu.timepit.refined._
import eu.timepit.refined.api._
import eu.timepit.refined.string._
import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString

object Domain {

  @newtype case class UserId(value: UUID)
  @newtype case class UserName(value: String)
  @newtype case class Password(value: String)
  @newtype case class JwtToken(value: String)

  @newtype case class BrandId(value: UUID)
  @newtype case class BrandName(value: String)
  @newtype case class Brand(value: String)

  @newtype case class CategoryId(value: UUID)
  @newtype case class Category(value: String)

  @newtype case class ItemId(value: UUID)
  @newtype case class ItemName(value: String)
  @newtype case class ItemDescription(value: String)

  @newtype case class Quantity(value: Int)
  @newtype case class Cart(items: Map[ItemId, Quantity])
  @newtype case class CartId(value: UUID)

  @newtype case class OrderId(uuid: UUID)
  @newtype case class PaymentId(uuid: UUID)

  @newtype case class USD(value: BigDecimal)

  case class Item(
    uuid: ItemId,
    name: ItemName,
    description: ItemDescription,
    price: USD,
    brand: Brand,
    category: Category
  )

  case class CreateItem(
    name: ItemName,
    description: ItemDescription,
    price: USD,
    brandId: BrandId,
    categoryId: CategoryId
  )

  case class UpdateItem(
    id: ItemId,
    price: USD
  )

  case class CartItem(item: Item, quantity: Quantity)
  case class CartTotal(items: List[CartItem], total: USD)

  case class Order(
    id: OrderId,
    pid: PaymentId,
    items: Map[ItemId, Quantity],
    total: USD
  )

  case class User(id: UserId, name: UserName)
  case class Card(number: String, validUntil: LocalDate)


}
