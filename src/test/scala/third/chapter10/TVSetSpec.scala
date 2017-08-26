package third.chapter10

import org.scalatest.{FeatureSpec, GivenWhenThen}

class TVSetSpec extends FeatureSpec with GivenWhenThen {

  feature("TV power button"){
    scenario("User presses power button when TV is off") {
      Given("a TV set that is switched off")
      When("the power button is pressed")
      Then("the TV should switch on")
      pending
    }
  }
}
