

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

    def hasValue(key: K): Boolean = safeGet(key).isDefined
  }

  //
  implicit class InExt[K](i: K) {
    def in(is: K*): Boolean = is.contains(i)

    def notIn(is: K*): Boolean = !is.contains(i)
  }

  //
  implicit class IntExt(i: Int) {
    def integer: Integer = Integer.valueOf(i)
  }

  //
  implicit class JLongExt(l: java.lang.Long) {
    def bigInteger: java.math.BigInteger = java.math.BigInteger.valueOf(l)
  }

  //
  implicit class StringExt(s: String) {
    def toInteger: Integer = s.toInt.integer

    def toBigInt: BigInt = BigInt(s)

    def toBigDecimal: BigDecimal = BigDecimal(s)
  }

  /** A supporting class which makes the function toFlatTupleList available for Maps */
  implicit class MapKeyFlattener(m: Map[String, Any]) {
    def flattenKeys(prefix: String = "root"): Map[String, Any] = {
      m.toSeq.flatMap {
        case (k, v) if v.isInstanceOf[Map[String, Any]] =>
          val vMap = v.asInstanceOf[Map[String, Any]]
          vMap.flattenKeys(s"$prefix.$k")
        case (k, v) if v.isInstanceOf[List[Map[String, Any]]] =>
          val vList = v.asInstanceOf[List[Map[String, Any]]]
          vList.indices.flatMap { idx =>
            vList(idx).flattenKeys(s"$prefix.$k.$idx")
          }
        case (k, v) => Seq((s"$prefix.$k", v))
      }.toMap
    }
  }


  def main(args: Array[String]): Unit = {
    println("a" in("a", "b", "c"))
    println(1 in(1, 2, 3))

    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGet("a"))
    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGetIfNoneExist("a")("d"))
    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGet2("a", "c"))
    println(Map("a" -> 1, "b" -> 2, "c" -> 3, "d" -> null).safeGet2("a", "d"))

    val m = Map(
      "name1" -> "value1",
      "nested1" -> Map("name1" -> "value1", "name2" -> "value2"),
      "nestedList1" -> List(
        Map("name1" -> "value1", "name2" -> "value2"),
        Map("name1" -> "value1", "name2" -> "value2")
      )
    )
    println
    println {
      m.flattenKeys()
        .toSeq
        .sortBy(_._1)
        .map {
          case (k, v) => s"$k = $v"
        }
        .mkString("\n")
    }
  }
}
