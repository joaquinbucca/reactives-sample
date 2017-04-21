/**
  * Created by jbucca on 4/21/17.
  */
object TwitterFilters {


  private val la12 : java.lang.Long = 249345746L
  private val claudioCiv : java.lang.Long = 172140725L
  private val augustoCes : java.lang.Long = 157131965L

  private val followings = List(la12, claudioCiv, augustoCes)
  private val terms = List("riquelme", "tevez", "centurion")

  def filters() : TwFilters = TwFilters(followings, terms)
}


case class TwFilters(followings: List[java.lang.Long], terms: List[String])