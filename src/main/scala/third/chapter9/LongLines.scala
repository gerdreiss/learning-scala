package third.chapter9

import scala.io.Source

object LongLines {

  def processFile(fullpath: String, width: Int) = {

    def print(line: String): Unit = {
      val filename = fullpath.substring(fullpath.lastIndexOf("/") + 1)
      val trimmed = line.trim
      println(filename + ": " + trimmed)
    }

    def tooLong(line: String): Boolean = {
      line.length > width
    }

    Source
      .fromFile(fullpath)
      .getLines()
      .filter(tooLong)
      .foreach(print)
  }
}
