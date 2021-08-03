object Lengths:
  opaque type Meters = Double
  def Meters(value: Double): Meters = value
  extension (x: Meters)
    def +(y: Meters): Meters = x + y
    def show: String         = s"$x m"

def usage(): Unit =
  import Lengths.*
  val twoMeters: Meters  = Meters(2.0)
  val fourMeters: Meters = twoMeters + twoMeters
  println(fourMeters.show)
  println(show(fourMeters))
