object Extensions {

  //
  implicit class SafeMapExt(map: Map[String, AnyRef]) {

    def safeGet(key: String): Option[AnyRef] = map.get(key) match {
      case Some(v) => Option(v) // in case Some contains a null, it's transformed to None
      case _ => None
    }

    def safeGetFirst(keys: String*): Option[AnyRef] = keys.find(map.contains).flatMap(safeGet)

    def safeGetIfNoneExist(key: String)(noneExist: String*): Option[AnyRef] = safeGetFirst(noneExist: _*) match {
      case None => safeGet(key)
      case _ => None
    }

    def safeGetFirstIfNoneExist(keys: String*)(noneExist: String*): Option[AnyRef] = safeGetFirst(noneExist: _*) match {
      case None => safeGetFirst(keys: _*)
      case _ => None
    }

    def safeGet2(key1: String, key2: String): Option[(AnyRef, AnyRef)] = (map.safeGet(key1), map.safeGet(key2)) match {
      case (None, _) | (_, None) => None
      case (Some(v1), Some(v2)) => Some(v1, v2)
    }
  }

  //
  implicit class IntExt(i: Int) {
    def in(is: Int*): Boolean = is.contains(i)
    def notIn(is: Int*): Boolean = !is.contains(i)
  }

}
