package example.myProvider

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object EvClient {
  def send(payload: String): String = {
    import cats.effect._
    import org.http4s.{Uri, _}
    import org.http4s.client._
    import org.http4s.client.blaze.BlazeClientBuilder
    import org.http4s.client.dsl.io._
    import org.http4s.dsl.io._

    import scala.concurrent.ExecutionContext.Implicits.global
    implicit val cs: ContextShift[IO] = IO.contextShift(global)

    // Call an echo server
    val uri = Uri.unsafeFromString("https://postman-echo.com/post")

    val client: Resource[IO, Client[IO]] =
      BlazeClientBuilder[IO](global).resource

    val post = POST(UrlForm("ev" -> payload), uri)

    val result: Future[String] = for {
      value2 <- client.use(_.expect[String](post)).unsafeToFuture()
    } yield value2

    Await.result(result, 5.seconds)
  }
}

