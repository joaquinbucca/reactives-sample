import java.util.concurrent.LinkedBlockingQueue

import com.twitter.hbc.ClientBuilder
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint
import com.twitter.hbc.core.{Constants, HttpHosts}
import com.twitter.hbc.core.event.Event
import com.twitter.hbc.core.processor.StringDelimitedProcessor
import com.twitter.hbc.httpclient.BasicClient
import com.twitter.hbc.httpclient.auth.OAuth1
import scala.collection.JavaConversions._


/**
  * Created by jbucca on 4/21/17.
  */
object TwClient {

  def client(credentials: TwCredentials) : (BasicClient, LinkedBlockingQueue[String])= {
    val msgQueue = new LinkedBlockingQueue[String](100000)
    val eventQueue = new LinkedBlockingQueue[Event](1000)
    val hosebirdHosts = new HttpHosts(Constants.STREAM_HOST)

    val filters = TwitterFilters.filters()

    val hosebirdEndpoint = new StatusesFilterEndpoint()

    hosebirdEndpoint.followings(filters.followings)
    hosebirdEndpoint.trackTerms(filters.terms)

    val hosebirdAuth = new OAuth1(credentials.consumerKey, credentials.consumerSecret, credentials.token, credentials.tokenSecret)

    val client = new ClientBuilder()
      .name("Boquita-streaming")                              // optional: mainly for the logs
      .hosts(hosebirdHosts)
      .authentication(hosebirdAuth)
      .endpoint(hosebirdEndpoint)
      .processor(new StringDelimitedProcessor(msgQueue))
      .eventMessageQueue(eventQueue)
      .build()

    (client, msgQueue)
  }
}
