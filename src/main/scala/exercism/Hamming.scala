package exercism

object Hamming {
  def distance(str1: String, str2: String): Option[Int] =
    if (str1.length != str2.length) None
    else
      Some {
        (str1 zip str2) count {
          case (a, b) => a != b
        }
      }

  def distance1(str1: String, str2: String): Option[Int] =
    (str1, str2) match {
      case (a, b) if a.length == b.length => Some {
        (a zip b) count (t => t != t.swap)
      }
      case _ => None
    }
}
