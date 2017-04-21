import com.typesafe.config.ConfigFactory

/**
  * Created by jbucca on 4/21/17.
  */
trait Config {

  private val config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")

  val httpHost = httpConfig.getString("interface")
  val httpPort = httpConfig.getInt("port")

  private val twitterConfig = config.getConfig("twitter")

  private val consumerKey = twitterConfig.getString("consumerKey")
  private val consumerSecret = twitterConfig.getString("consumerSecret")
  private val token = twitterConfig.getString("token")
  private val tokenSecret = twitterConfig.getString("tokenSecret")

  val twCredentials = TwCredentials(consumerKey, consumerSecret, token, tokenSecret)
}

case class TwCredentials(consumerKey: String, consumerSecret: String, token: String, tokenSecret: String)