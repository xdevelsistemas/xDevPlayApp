package controllers

import play.api.mvc._
import securesocial.core.{SecureSocial, Identity}

/**
 * Created by claytonsantosdasilva on 10/07/14.
 */
class xDevController extends Controller  with securesocial.core.SecureSocial {


  def _user(implicit request: RequestHeader): Option[Identity] = {
    return SecureSocial.currentUser
  }

  def _userdao : dao.UserDAO = new dao.UserDAO()



}
