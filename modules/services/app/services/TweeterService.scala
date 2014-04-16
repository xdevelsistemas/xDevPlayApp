package services

import twitter4j._
import twitter4j.conf.ConfigurationBuilder

import models.{TweetTag, Tweet}
import dao.TweetTagDAO
import play.libs.F
import play.db.jpa.JPA
import play.{Play, Configuration}


class TweeterService {

  private val conf: Configuration = Play.application().configuration()

  private val cb : ConfigurationBuilder = new ConfigurationBuilder
  cb.setDebugEnabled(conf.getBoolean("tweeter4j.debug"))
    .setOAuthConsumerKey      (conf.getString("tweeter4j.oauth.consumerKey"))
    .setOAuthConsumerSecret   (conf.getString("tweeter4j.oauth.consumerSecret"))
    .setOAuthAccessToken      (conf.getString("tweeter4j.oauth.accessToken"))
    .setOAuthAccessTokenSecret(conf.getString("tweeter4j.oauth.accessTokenSecret"))

  private val tf : TwitterFactory = new TwitterFactory(cb.build())
  private val twitter : Twitter = tf.getInstance


  def search(query: String) : TweetTag =
    JPA.withTransaction("default", false, new F.Function0[TweetTag] {
      def apply: TweetTag = {
        val tweetTagDAO = new TweetTagDAO
        tweetTagDAO.create(query, twitter.search(new Query(query)).getTweets, true)
      }
    })

  def ordered(o: TweetTag) : java.util.List[Tweet] =
    JPA.withTransaction("default", true, new F.Function0[java.util.List[Tweet]] {
      def apply: java.util.List[Tweet] = {
        val tweetTagDAO = new TweetTagDAO
        tweetTagDAO.orderByDate(o)
      }
    })

  def retweets(o: TweetTag) : Int =
    JPA.withTransaction("default", true, new F.Function0[Int] {
      def apply: Int = {
        val tweetTagDAO = new TweetTagDAO
        tweetTagDAO.countRetweets(o)
      }
    })

}
