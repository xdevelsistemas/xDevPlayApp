package controllers.Proconsorcio

import java.util

import _root_.util.{TpDropDown, TpElDropDown}
import play.api.cache.Cached
import play.api.libs.json.Json
import play.api.mvc.Action
import scala.collection.JavaConverters._
import br.com.republicavirtual.{CepService, CepServiceVO}
import controllers.xDevRestController
import dao.AdministradoraDAO
import models.Proconsorcio.Administradora
import play.mvc.Result
import securesocial.core.java.SecureSocial
import play.api.Play.current


/**
 * Created by claytonsantosdasilva on 01/08/14.
 * Controller responável por toda comunicação via rest com o front-end
 *
 */
object RestController extends xDevRestController {

  def administradoras() = Cached("list.administradoras") {
    Action {

      val lista = new util.ArrayList[TpElDropDown]()
      val resp: TpDropDown = new TpDropDown().apply("uuid","Administradora", lista).asInstanceOf[TpDropDown]



      JsonResult(resp)


    }
  }



  def endereco(cep: String) = Action  {
    JsonResult(CepService.buscaCEP(cep))
  }


}
