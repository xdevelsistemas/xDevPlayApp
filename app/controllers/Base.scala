package controllers

import javax.persistence.EntityManager

import play.api.mvc._
import play.db.jpa.JPA
import play.db.jpa.{Transactional, JPA}
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
