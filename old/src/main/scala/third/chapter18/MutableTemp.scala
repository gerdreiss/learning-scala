package third.chapter18

object MutableTemp {

  class Thermometer {

    var celsius: Float = _

    def fahrenheit: Float = celsius * 9 / 5 + 32

    def fahrenheit_=(f: Float): Unit = {
      celsius = (f - 32) * 5 / 9
    }

    override def toString = s"$celsius C / $fahrenheit F"
  }

  def main(args: Array[String]): Unit = {
    val t = new Thermometer
    t.fahrenheit = 100
    println(t)
  }
}