import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.util.{Try, Using}

Using(Source.fromFile("src/main/scala/Task.scala"))(_.mkString)

val confused: scala.util.Try[Throwable] = Try(Throwable("Am I a success ?"))


