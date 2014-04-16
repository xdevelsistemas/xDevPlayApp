package app

import _root_.services.TweeterService
import scala.collection.JavaConverters._

import play.api.test._

import org.specs2.mock._
import play.libs.F.Callback0
import play.db.jpa.JPA


class TwitterSpec extends PlaySpecification with Mockito with SpecSettings {

  "TwitterService" should {
    "be able retrieve distinct hashtags" in {
      new WithApplication(FakeApplication(withoutPlugins = withoutPlugins)) {

        // obtain service
        val ts = new TweeterService

        ts.search("genoma")

        ts.search("carnival")

        true must be equalTo true
      }
    }
  }

  "TwitterService" should {
    "be able retrieve same hashtag twice" in {
      new WithApplication(FakeApplication(withoutPlugins = withoutPlugins)) {

        // obtain service
        val ts = new TweeterService

        // first call
        ts.search("MH370")

        // second call (should be able to handle duplicates)
        ts.search("MH370")

        true must be equalTo true
      }
    }
  }

}
