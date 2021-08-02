scala.util.Using(scala.io.Source.fromFile("src/main/scala/Task.scala"))(_.mkString)

val confused: scala.util.Try[Throwable] = scala.util.Try(Throwable("Am I a success ?"))
