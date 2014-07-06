package controllers

import play.api.Logger
import play.api.mvc._

import securesocial.core.SecureSocial
import services.AuthenticationService
import play.db.jpa.Transactional


object Authentication extends Controller with SecureSocial with Authentication


trait Authentication {
  this: SecureSocial =>

  val logger = Logger("controllers.Authentication")

//  def credentials = SecuredAction { implicit request =>
//    logger.warn("logging from application")
//    Ok(views.html.credentials(request.user))
//  }
//
//  def identities = SecuredAction { implicit request =>
//    import play.api.Play.current
//    Ok(views.html.identities(
//      request.user,
//      com.typesafe.plugin.use[AuthenticationService].findByEmail(request.user.email)))
//  }

}
