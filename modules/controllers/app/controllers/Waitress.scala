package controllers

import play.api.Logger
import play.api.mvc._

import securesocial.core.SecureSocial


object Waitress extends Controller with SecureSocial with Waitress


trait Waitress {
  this: SecureSocial =>

  val logger = Logger("controllers.Waitress")


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
