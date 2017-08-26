package third.chapter20

object InitializingAbstractVals {

  trait RationalTrait {
    val numerArg: Int
    val denomArg: Int

    require(denomArg != 0)

    private val g = gcd(numerArg, denomArg)
    val numer: Int = numerArg / g
    val denom: Int = denomArg / g

    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)

    override def toString: String = numer + "/" + denom
  }

  object twoThirds extends {
    val numerArg: Int = 2
    val denomArg: Int = 2
  } with RationalTrait

  class RationalClass(n: Int, d: Int) extends {
    val numerArg: Int = n
    val denomArg: Int = d
  } with RationalTrait {
    def +(that: RationalClass) = new RationalClass(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  }

  trait LazyRationalTrait {
    val numerArg: Int
    val denomArg: Int
    lazy val numer: Int = numerArg / g
    lazy val denom: Int = denomArg / g
    override def toString: String = numer + "/" + denom
    private lazy val g = {
      require(denomArg != 0)
      gcd(numerArg, denomArg)
    }
    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
  }

  def main(args: Array[String]): Unit = {
    val x = 2

    // this won't work - requirement fails
    // val r = new RationalTrait {
    //  override val denomArg: Int = 1 * x
    //  override val numerArg: Int = 2 * x
    // }
    // println(r)

    // this doesn't work either,
    // because there is no 'this' at the time
    // val r = new {
    //  val numerArg: Int = 1
    //  val denomArg: Int = this.numerArg * 2
    // } with RationalTrait

    // that will work, though
    val lr = new LazyRationalTrait {
      override val denomArg: Int = 1 * x
      override val numerArg: Int = 2 * x
    }
    println(lr)

    // and this will work, too
    val r2 = new {
      val numerArg: Int = 1 * x
      val denomArg: Int = 2 * x
    } with RationalTrait

    println(r2)
    println()

    val rc1 = new RationalClass(1, 3)
    val rc2 = new RationalClass(2, 7)
    println(rc1 + " + " + rc2 + " = " + (rc1 + rc2))
  }
}
