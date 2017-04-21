import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import rx.lang.scala.Observable

import scala.collection.JavaConversions._

/**
  * Created by jbucca on 4/17/17.
  */
object Main extends App with Config {

  implicit val system = ActorSystem()
  implicit val executor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  val (hosebirdClient, queue) = TwClient.client(twCredentials)
  // Attempts to establish a connection.
  hosebirdClient.connect()


  var observable : Observable[String] = Observable.from[String](queue)
  observable.subscribe(println(_))

//    .subscribeOn(Schedulers.io())
//    .observeOn(Schedulers.immediate())

  while (!hosebirdClient.isDone) {
    val take = queue.take()
    observable = observable :+ take
    observable.subscribe(println(_))
  }

}
