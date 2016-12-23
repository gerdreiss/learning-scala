package com.jscriptive.scala.third.chapter23

object Books {

  case class Book(title: String, authors: String*)

  val books: List[Book] =
    List(
      Book(
        "Structure and Interpretation of Computer Programs",
        "Abelson, Harold", "Sussman, Gerald J."
      ),
      Book(
        "Principles of Compiler Design",
        "Aho, Alfred", "Ullman, Jeffrey"
      ),
      Book(
        "Elements of ML Programming",
        "Ullman, Jeffrey"
      ),
      Book(
        "The Java Language Specification",
        "Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
      )
    )

  // remove duplicates
  def removeDuplicates[A](xs: List[A]): List[A] = {
    if (xs.isEmpty) xs
    else
      xs.head :: removeDuplicates(
        xs.tail filter (x => x != xs.head)
      )
  }

  def main(args: Array[String]): Unit = {
    // find the titles of books of  Gosling
    val goslings = for {
      b <- books
      a <- b.authors
      if a startsWith "Gosling"
    } yield b.title
    println(goslings)

    // find the titles of books with Program in the title
    val programs = for {
      b <- books
      if b.title contains "Program"
    } yield b.title
    println(programs)

    // find authors with at least two books
    val authors = for {
      b1 <- books; b2 <- books if b1 != b2
      a1 <- b1.authors; a2 <- b2.authors if a1 == a2
    } yield a1
    println(authors)
    println(removeDuplicates(authors))
  }
}
