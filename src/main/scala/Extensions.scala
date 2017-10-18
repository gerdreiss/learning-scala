object Extensions {

  //
  implicit class SafeMapExt[K, V](map: Map[K, V]) {

    def safeGet(key: K): Option[V] =
      map.get(key) match {
        case Some(v) => Option(v) // in case Some contains a null, it's transformed to None
        case _ => None
      }

    def safeGetFirst(keys: K*): Option[V] =
      keys.find(map.contains).flatMap(safeGet)

    def safeGetIfNoneExist(key: K)(noneExist: K*): Option[V] =
      safeGetFirst(noneExist: _*) match {
        case None => safeGet(key)
        case _ => None
      }

    def safeGetFirstIfNoneExist(keys: K*)(noneExist: K*): Option[V] =
      safeGetFirst(noneExist: _*) match {
        case None => safeGetFirst(keys: _*)
        case _ => None
      }

    def safeGet2(key1: K, key2: K): Option[(V, V)] =
      (map.safeGet(key1), map.safeGet(key2)) match {
        case (Some(v1), Some(v2)) => Some(v1, v2)
        case _ => None
      }
  }

  //
  implicit class InExt[K](i: K) {
    def in(is: K*): Boolean = is.contains(i)
    def notIn(is: K*): Boolean = !is.contains(i)
  }


  def main(args: Array[String]): Unit = {
    println("a" in("a", "b", "c"))
    println(1 in(1, 2, 3))


    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGet("a"))
    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGetIfNoneExist("a")("d"))
    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGet2("a", "c"))
    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGet2("a", "d"))

  }

}
