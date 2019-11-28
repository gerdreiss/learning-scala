package shoppingcart

object Algebras {

  import Domain._

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
