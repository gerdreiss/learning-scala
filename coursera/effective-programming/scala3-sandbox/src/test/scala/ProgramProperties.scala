import org.scalacheck.{Gen, Prop}

class ProgramProperties extends munit.ScalaCheckSuite:

  val fibonacciDomain: Gen[Int] = Gen.choose(3, 46)

  property("fibonacci(n) == fibonacci(n - 1) + fibonacci(n - 2)") {
    Prop.forAll(fibonacciDomain) { (n: Int) =>
      fibonacci(n) == fibonacci(n - 1) + fibonacci(n - 2)
    }
  }

  property("fibonacci numbers are positive") {
    Prop.forAll(fibonacciDomain) { (n: Int) =>
      fibonacci(n) >= 0
    }
  }
