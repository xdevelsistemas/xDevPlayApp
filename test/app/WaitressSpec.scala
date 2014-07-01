package app

import scala.concurrent.Future

import play.api.mvc._
import play.api.test._

import org.json4s._
import org.json4s.jackson.JsonMethods

import securesocial.core.SecureSocial


class WaitressSpec extends PlaySpecification {

//  class TestController extends Controller with SecureSocial with controllers.Waitress {
//
//    "/app" should {
//      "return a valid JSON message" in {
//        val controller = new TestController
//        val result: Future[SimpleResult] = controller.app().apply(FakeRequest())
//
//        val r: String = contentAsString(result)
//        val h = headers(result)
//
//        val json: JValue = JsonMethods.parse(r)
//
//        implicit lazy val formats = DefaultFormats
//        (json \ "username").extract[String] must be equalTo "bob@example.com"
//
//        h.get(ACCESS_CONTROL_ALLOW_ORIGIN).get must be equalTo "*, "
//        h.get(ACCESS_CONTROL_ALLOW_METHODS).get must be equalTo "POST, GET, OPTIONS"
//        h.get(ACCESS_CONTROL_ALLOW_HEADERS).get must be equalTo "Origin, X-Requested-With, Content-Type, Accept"
//      }
//    }
//  }

}
