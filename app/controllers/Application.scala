package controllers

import play.api.Logger
import play.api.mvc._

import securesocial.core.SecureSocial
import common.WithProvider
import play.db.jpa.Transactional


object Application extends Controller with SecureSocial with Application


trait Application {
  this: SecureSocial =>

  val logger = Logger("controllers.Application")

  def index() = Action {
    Redirect("/home")
  }

  // a sample action using the new authorization hook
  def onlyUserPass = SecuredAction(WithProvider("userpass")) { implicit request =>
    logger.error("only userpass")
//
//    Note: If you had a User class and returned an instance of it from UserService, this
//          is how you would convert Identity to your own class:
//
//    request.user match {
//      case user: User => // do whatever you need with your user class
//      case _ => // did not get a User instance, should not happen,log error and throw exception
//    }
    Ok("You can see this because you logged in using username/password")
  }

}
