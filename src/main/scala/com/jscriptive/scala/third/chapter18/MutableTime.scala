package com.jscriptive.scala.third.chapter18

object MutableTime {

  //class Time {
  //  var hour = 12
  //  var minute = 0
  //}
  // This is equal to:

  sealed trait TimeOfDay
  case object AM extends TimeOfDay
  case object PM extends TimeOfDay
  val timesOfDay = Seq(AM, PM)

  class Time {

    private[this] var h: Int = 12
    private[this] var m: Int = 0
    private[this] var tod: TimeOfDay = AM

    def hour: Int = h

    def hour_=(x: Int): Unit = {
      require(0 <= x && x < 24)
      h = x
    }

    def minute: Int = m

    def minute_=(x: Int): Unit = {
      require(0 <= x && x < 60)
      m = x
    }

    def timeOfDay: TimeOfDay = tod

    def timeOfDay_=(x: TimeOfDay): Unit = {
      require(x != null)
      tod = x
    }

    override def toString = s"it's $hour:$minute $timeOfDay"
  }

  def main(args: Array[String]): Unit = {
    val t = new Time
    t.hour = 10
    t.minute = 30
    t.timeOfDay = PM
    println(t)
  }
}
