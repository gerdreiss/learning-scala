package fp.red.chapter8

import fp.red.chapter8.Prop.{FailedCase, SuccessCount}

object Prop {
  type FailedCase = String
  type SuccessCount = Int
}

trait Prop {
  def check: Either[(FailedCase, SuccessCount), SuccessCount]
  def &&(p: Prop): Prop = new Prop {
    override def check: Either[(FailedCase, SuccessCount), SuccessCount] = ???
  }
}
