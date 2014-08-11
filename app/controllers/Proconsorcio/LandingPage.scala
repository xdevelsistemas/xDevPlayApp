package controllers.Proconsorcio

import controllers.xDevController
import dao.UserIdentity
import play.api.mvc._
import securesocial.core.java.SecureSocial


/**
 * Created by claytonsantosdasilva on 10/07/14.
 *
 * Controller responsável pelo processamento da landingPage
 *
 */
object LandingPage extends xDevController{
  def Home = UserAwareAction { implicit request =>
    if(!_user.isDefined){Ok(views.html.LandingPage.main())}else{Redirect("/home")}
  }

}
