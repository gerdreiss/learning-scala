package fp.tothemax

import fp.tothemax.Algebra.{Console, Program, Random}

object TestAlgebra {

  case class TestData(input: List[String], output: List[String], nums: List[Int]) {
    def putStrLn(line: String): (TestData, Unit) = (copy(output = line :: output), ())
    def getStrLn: (TestData, String) = (copy(input = input.drop(1)), input.head)
    def nextInt(upper: Int): (TestData, Int) = (copy(nums = nums.drop(1)), nums.head)
    def showResults: String = output.reverse.mkString("\n")
  }

  case class TestIO[A](run: TestData => (TestData, A)) {
    self =>
    def map[B](ab: A => B): TestIO[B] = TestIO(t => self.run(t) match {
      case (t0, a) => (t0, ab(a))
    })
    def flatMap[B](afb: A => TestIO[B]): TestIO[B] = TestIO(t => self.run(t) match {
      case (t0, a) => afb(a).run(t0)
    })
    def eval(t: TestData): TestData = run(t)._1
  }

  object TestIO {
    def point[A](a: => A): TestIO[A] = TestIO(t => (t, a))

    implicit val ProgramIO = new Program[TestIO] {
      def finish[A](a: => A): TestIO[A] = TestIO.point(a)
      def chain[A, B](fa: TestIO[A], afb: A => TestIO[B]): TestIO[B] = fa.flatMap(afb)
      def map[A, B](fa: TestIO[A], ab: A => B): TestIO[B] = fa.map(ab)
    }
    implicit val ConsoleIO = new Console[TestIO] {
      def putStrLn(line: String): TestIO[Unit] = TestIO(_.putStrLn(line))
      def getStrLn: TestIO[String] = TestIO(_.getStrLn)
    }
    implicit val RandomIO = new Random[TestIO] {
      def nextInt(upper: Int): TestIO[Int] = TestIO(_.nextInt(upper))
    }
  }

}