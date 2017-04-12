import java.net.InetAddress

import fs2._
import org.http4s.MediaType._
import org.http4s._
import org.http4s.dsl._
import org.http4s.headers._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp

import scala.concurrent._

object APIService extends StreamApp {

  def rootService(implicit ec: ExecutionContext = ExecutionContext.global) = HttpService {
    case _ -> Root =>
      // The default route result is NotFound. Sometimes MethodNotAllowed is more appropriate.
      MethodNotAllowed()

    case req@POST -> Root / "compute" =>
      req.decode[String] {
        str: String => Ok(HubStar.compute(str)).putHeaders(`Content-Type`(`application/json`))
      }
  }

  override def main(args: List[String]): Stream[Task, Nothing] = {
    val port = sys.env.get("PORT").map(_.toInt).getOrElse(8080)

    println(s"serving on host: ${InetAddress.getLoopbackAddress.getHostAddress}")
    println(s"serving on port: $port")

    BlazeBuilder
      .bindHttp(port, "0.0.0.0")
      .mountService(rootService, "/")
      .serve
  }
}