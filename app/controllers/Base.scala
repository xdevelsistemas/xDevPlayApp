package controllers

import javax.persistence.EntityManager
import com.fasterxml.jackson.core.JsonProcessingException
import play.db.jpa.{JPA}
import util._
import securesocial.core.{SecureSocial, Identity}
import play.api.libs.json.Json
import play.api.mvc._
import securesocial.core._

/**
 * Created by claytonsantosdasilva on 10/07/14.
 */
class xDevController extends Controller  with securesocial.core.SecureSocial {


  def _user(implicit request: RequestHeader): Option[Identity] = {
    return SecureSocial.currentUser
  }

  def _userdao : dao.UserDAO = new dao.UserDAO()

  def em: EntityManager = {
    JPA.em("default")
  }



}


class xDevRestController extends  xDevController{

  def JsonResult(yObject: xDevSerialize): SimpleResult = {
    try {
      Ok(yObject.serialize())
    }
    catch {
      case e: JsonProcessingException => {
        return BadRequest(new TpResponse("0",e.getMessage).serialize())
      }
    }
  }

}


//TODO desenvolver autenticacao externa via rest
class AuthController extends Controller {
  private implicit val readsOAuth2Info = Json.reads[OAuth2Info]
  // Some of the below code is taken from ProviderController in SecureSocial
  def authenticateMobile(providerName: String) = Action(parse.json) { implicit request =>
    // format: { "accessToken": "..." }
    val oauth2Info = request.body.asOpt[OAuth2Info]
    val provider = Registry.providers.get(providerName).get
    val filledUser = provider.fillProfile(
      SocialUser(IdentityId("", provider.id), "", "", "", None, None, provider.authMethod, oAuth2Info = oauth2Info))
    UserService.find(filledUser.identityId) map { user =>
      val newSession = Events.fire(new LoginEvent(user)).getOrElse(session)
      Authenticator.create(user).fold(
        error => throw error,
        authenticator => Ok(Json.obj("sessionId" -> authenticator.id))
          .withSession(newSession - SecureSocial.OriginalUrlKey - IdentityProvider.SessionId - OAuth1Provider.CacheKey)
          .withCookies(authenticator.toCookie)
      )
    } getOrElse NotFound(Json.obj("error" -> "user not found"))
  }
  // any other methods you might have relating to authentication ...
}
