case class Position(x: Double, y: Double):
  def distanceTo(that: Position): Double                 = ???
  def distanceToLine(line: (Position, Position)): Double = ???

object Position:
  val player = Position(0, 1.80)
  val hoop   = Position(6.75, 3.048)

case class Angle(radians: Double)
case class Speed(mps: Double)

def isWinningShot(angle: Angle, speed: Speed): Boolean =
  import Position._

  val v0X = speed.mps * math.cos(angle.radians)
  val v0Y = speed.mps * math.sin(angle.radians)
  val p0X = player.x
  val p0Y = player.y
  val g   = -9.81

  def goesThoughHoop(line: (Position, Position)): Boolean =
    hoop.distanceToLine(line) < 0.01

  def isNotTooFar(position: Position): Boolean =
    position.y > 0 && position.x <= hoop.x + 0.01

  def position(t: Int): Position =
    val x = p0X + v0X * t
    val y = p0Y + v0Y * t + 0.5 * g * t * t
    Position(x, y)

  val timings   = LazyList.from(0)
  val positions = timings.map(position)
  val lines     = positions.zip(positions.tail)

  lines
    .takeWhile((p1, _) => isNotTooFar(p1))
    .exists(goesThoughHoop)

  false

end isWinningShot
