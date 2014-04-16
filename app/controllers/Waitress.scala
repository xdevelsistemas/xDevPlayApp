package controllers

import play.api.Logger
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import securesocial.core.SecureSocial

import services.TweeterService



object Waitress extends Controller with SecureSocial with Waitress

trait Waitress {
  this: SecureSocial =>

  val logger = Logger("controllers.Waitress")


  val tweetForm = Form(
    "query" -> nonEmptyText
  )


  def trends = Action {
    Ok(views.html.tweets(None, 0, tweetForm))
  }

  def search = Action { implicit request =>
    tweetForm.bindFromRequest.fold(
      errors => BadRequest(views.html.tweets(None, 0, errors)),
      query => {
        val service = new TweeterService
        val o = service.search(query)
        val ordered = service.ordered(o)
        val retweets = service.retweets(o)
        Ok(views.html.tweets(Some(ordered), retweets, tweetForm))
      }
    )
  }


  // --------------------------------------------


  def app() = Action {

    val str: String =
      """{
        "properties": {
          "name1": "value1",
          "name2": "value2",
          "name3": "value3",
          "name4": "value4",
          "name5": "value5" }
      }"""

    Ok(str).withHeaders(
      ACCESS_CONTROL_ALLOW_ORIGIN -> "*, ",
      ACCESS_CONTROL_ALLOW_METHODS -> "POST, GET, OPTIONS",
      ACCESS_CONTROL_ALLOW_HEADERS -> "Origin, X-Requested-With, Content-Type, Accept",
      CACHE_CONTROL -> "max-age=3600")
  }


}
