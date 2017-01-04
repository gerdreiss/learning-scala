package com.jscriptive.scala.third.chapter26

object Extractors {

  object Domain {
    // The injection method
    def apply(parts: String*): String = parts.reverse.mkString(".")

    // The extraction method
    def unapplySeq(whole: String): Option[Seq[String]] = Some(whole.split("\\.").reverse)
  }

  object Email extends ((String, String) => String) {
    // The injection method
    def apply(user: String, domain: String): String = user + "@" + domain

    // The extraction method
    def unapply(str: String): Option[(String, String)] = {
      val parts = str split "@"
      if (parts.length == 2) Some(parts(0), parts(1)) else None
    }
  }

  object ExpandedEmail {
    def unapplySeq(email: String): Option[(String, Seq[String])] = {
      val parts = email split "@"
      if (parts.length == 2) Some(parts(0), parts(1).split("\\.").reverse) else None
    }
  }

  object Twice {
    // The injection method
    def apply(s: String): String = s + s

    // The extraction method
    def unapply(s: String): Option[String] = {
      val halfs = s.splitAt(s.length / 2)
      if (halfs._1 == halfs._2) Some(halfs._1) else None
    }
  }

  object UpperCase {
    def unapply(s: String): Boolean = s.toUpperCase == s
  }

  def userTwiceUpper(s: String): String = s match {
    case Email(Twice(x@UpperCase()), domain) =>
      "match: " + x + " in domain " + domain
    case _ => "no match"
  }

  def isTomInDotCom(s: String): Boolean = s match {
    case Email("tom", Domain("com", _*)) => true
    case _ => false
  }

  def main(args: Array[String]): Unit = {
    assert(Some("didi", "hotmail.com") == Email.unapply(Email.apply("didi", "hotmail.com")))

    println(userTwiceUpper("DIDI@hotmail.com"))
    println(userTwiceUpper("DIDO@hotmail.com"))
    println(userTwiceUpper("didi@hotmail.com"))

    println(isTomInDotCom("tom@sun.com"))
    println(isTomInDotCom("peter@sun.com"))
    println(isTomInDotCom("peter@acm.org"))

    // Wow!
    val ExpandedEmail(name, topdom, subdoms@_*) = "tom@support.epfl.ch"
    println(name)
    println(topdom)
    subdoms foreach println
  }
}
