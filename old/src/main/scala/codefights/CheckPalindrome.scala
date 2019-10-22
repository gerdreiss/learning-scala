package codefights

object CheckPalindrome extends App {

  def checkPalindrome(inputString: String): Boolean = {
    inputString.startsWith(inputString.substring(inputString.length / 2).reverse)
  }

  println(checkPalindrome("a"))
  println(checkPalindrome("aa"))
  println(checkPalindrome("ab"))
  println(checkPalindrome("abb"))
  println(checkPalindrome("aba"))
  println(checkPalindrome("abba"))
  println(checkPalindrome("abbac"))
  println(checkPalindrome("abbcbba"))
  println(checkPalindrome("abbcbbab"))

}
