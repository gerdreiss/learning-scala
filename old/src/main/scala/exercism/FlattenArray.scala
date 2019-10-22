package exercism

object FlattenArray {
  def flatten(list: List[Any]): List[Any] = {
    list.flatMap {
      case v: List[_] => flatten(v)
      case v: Any     => List(v)
      case _          => Nil
    }
  }
}
