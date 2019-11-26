package shoppingcart

import java.time.LocalDate
import java.util.UUID

import io.estatico.newtype.macros.newtype

object Algebras {

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

  trait Brands[F[_]] {
    def findAll: F[List[Brand]]
    def create(brand: Brand): F[Unit]
  }

  trait Categories[F[_]] {
    def findAll: F[List[Category]]
    def create(category: Category): F[Unit]
  }

  trait Items[F[_]] {
    def findAll: F[List[Item]]
    def findBy(brand: BrandName): F[List[Item]]
    def findById(itemId: ItemId): F[Option[Item]]
    def create(item: CreateItem): F[Unit]
    def update(item: UpdateItem): F[Unit]
  }

  trait ShoppingCart[F[_]] {
    def get(userId: UserId): F[CartTotal]
    def add(userId: UserId, itemId: ItemId, quantity: Quantity): F[Unit]
    def delete(userId: UserId): F[Unit]
    def removeItem(userId: UserId, itemId: ItemId): F[Unit]
    def update(userId: UserId, cart: Cart): F[Unit]
  }

  trait Orders[F[_]] {
    def get(
        userId: UserId,
        orderId: OrderId
    ): F[Option[Order]]

    def findBy(userId: UserId): F[List[Order]]

    def create(
        userId: UserId,
        paymentId: PaymentId,
        items: List[CartItem],
        total: USD
    ): F[OrderId]
  }

  trait Users[F[_]] {
    def find(
        username: UserName,
        password: Password
    ): F[Option[User]]

    def create(
        username: UserName,
        password: Password
    ): F[UserId]
  }

  trait Auth[F[_]] {
    def findUser(token: JwtToken): F[Option[User]]
    def newUser(username: UserName, password: Password): F[JwtToken]
    def login(username: UserName, password: Password): F[JwtToken]
    def logout(token: JwtToken, username: UserName): F[Unit]
  }

  trait PaymentClient[F[_]] {
    def process(
      userId: UserId,
      total: USD,
      card: Card
    ): F[PaymentId]
  }
}
