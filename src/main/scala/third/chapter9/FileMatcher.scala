package third.chapter9

import java.io.File

object FileMatcher {

  private def filesHere: Array[File] = new File(".").listFiles()

  // def filesMatching(query: String, matcher: (String, String) => Boolean) =
  //   filesHere.filter(f => matcher(f.getName, query))
  //
  // def filesEnding(query: String) = filesMatching(query, _ endsWith _)
  // def filesContaining(query: String) = filesMatching(query, _ contains _)
  // def filesRegex(query: String) = filesMatching(query, _ matches _)

  // better versions, using closures:
  def filesMatching(matcher: String => Boolean) = filesHere.filter(f => matcher(f.getName))

  def filesEnding(query: String) = filesMatching(_ endsWith query)

  def filesContaining(query: String) = filesMatching(_ contains query)

  def filesRegex(query: String) = filesMatching(_ matches query)

}
