package controllers


import play.api.Logger
import play.api.mvc._

import securesocial.core.SecureSocial



object Application extends Controller with SecureSocial with Application


trait Application {
  this: SecureSocial =>

  val logger = Logger("controllers.Application")

  def index() = Action {
    Redirect("/home")
  }

}
