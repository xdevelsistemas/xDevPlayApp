package controllers

import javax.persistence.EntityManager
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.{ObjectMapper, ObjectWriter}
import play.api.mvc._
import play.db.jpa.{JPA}
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



//  def persist (block: Unit ) = {
//    def exec = block
//    JPA.withTransaction("default", false, new F.Function0[Void]  {
//      def apply: Void = {
//
//        exec
//
//        return null
//      }
//
//    })
//
//  }



  def _userdao : dao.UserDAO = new dao.UserDAO()

  def em: EntityManager = {
    JPA.em("default")
  }



}


class xDevRestController extends  xDevController{

  def JsonResult(yObject: Object): Result = {
    val ow: ObjectWriter = new ObjectMapper().writer.withDefaultPrettyPrinter
    try {
      val json: String = ow.writeValueAsString(yObject)

      return Results.ok(Json.toJson(yObject))
    }
    catch {
      case e: JsonProcessingException => {
        return Results.badRequest(e.getMessage)
      }
    }
  }

}
