package tlp


trait Status
trait Open extends Status
trait Closed extends Status

trait Door[S <: Status]

object Door {
  def apply[S <: Status]: Door[S] = new Door[S] {}
  def open[S <: Closed](d: Door[S]): Door[Open] = Door[Open]
  def close[S <: Open](d: Door[S]): Door[Closed] = Door[Closed]
}

object PhantomTypes {

  val closedDoor = Door[Closed]
  val openDoor = Door.open(closedDoor)
  val closedAgainDoor = Door.close(openDoor)

  // val closedClosedDoor = Door.close(closedDoor)
  // val openOpenDoor = Door.open(openDoor)
}
