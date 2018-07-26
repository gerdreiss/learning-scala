object Hamming {
  def distance(str1: String, str2: String): Option[Int] =
    if (str1.length != str2.length) None
    else
      Some {
        (str1 zip str2) count {
          case (a, b) => a != b
        }
      }

}