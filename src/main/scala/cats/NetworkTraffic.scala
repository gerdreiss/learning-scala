package cats

import scala.util.{Failure, Random, Success}

object NetworkTraffic extends App {

  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future

  def getTrafficFromHost(hostname: String): Future[Int] =
    Future {
      Random.nextInt(10)
    }

  def getTrafficFromAllHosts: Future[Int] =
    for {
      traffic1 <- getTrafficFromHost("host1")
      traffic2 <- getTrafficFromHost("host2")
      traffic3 <- getTrafficFromHost("host3")
    } yield traffic1 + traffic2 + traffic3

  getTrafficFromAllHosts.onComplete {
    case Success(t) => println(s"Traffic: $t")
    case Failure(e) => e.printStackTrace()
  }

}
