package controllers

import javax.persistence.EntityManager
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.{ObjectMapper, ObjectWriter}
import play.api.libs.json.JsValue
import play.api.mvc._
import play.db.jpa.{JPA}
import util._
import play.libs.Json
import play.mvc.{Results, Result}
import securesocial.core.{SecureSocial, Identity}

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
        return BadRequest(((new TpResponse()).apply("0",e.getMessage)).asInstanceOf[TpResponse].serialize())
      }
    }
  }

}
