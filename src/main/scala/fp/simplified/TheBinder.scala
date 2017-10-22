package fp.simplified

object TheBinder {

  def bind(fun: (Int) => (Int, String), tup: (Int, String)): (Int, String) = {
    val givenInt = tup._1
    val givenStr = tup._2

    // apply the given function to the given int
    val (intRes, strRes) = fun(givenInt)

    // append strRes to the given string
    val newString = givenStr + strRes

    // return the new int and string
    (intRes, newString)
  }
}
