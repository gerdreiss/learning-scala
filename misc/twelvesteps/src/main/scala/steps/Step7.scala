package steps

import monocle.Prism

class Step7 {

  // don't do type bounds, unless you absolutely have to

  trait ProductID
  trait MovieID extends ProductID

  trait Product[T] {
    def it: T
    def prism: Prism[ProductID, T]
  }

  trait Movie extends Product[MovieID]

}
