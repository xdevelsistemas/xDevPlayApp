package controllers.Proconsorcio

import java.util

import RestModels.ListaContaBanco
import _root_.util.{TpDropDown, TpElDropDown}
import play.api.cache.Cached
import play.api.mvc.Action
import scala.collection.JavaConverters._
import br.com.republicavirtual.CepService
import controllers.xDevRestController
import dao.{ContaBancoDAO, TipoCartaDAO, AdministradoraDAO}
import play.api.Play.current


/**
 * Created by claytonsantosdasilva on 01/08/14.
 * Controller responável por toda comunicação via rest com o front-end
 *
 */
object RestController extends xDevRestController {

  def getAdministradoras = Cached("list.administradoras") {
    Action {

      val lista = new util.ArrayList[TpElDropDown]()
      val resp = new TpDropDown("uuid", "Administradora",

        (new util.ArrayList[TpElDropDown]() {
          t =>
          (new AdministradoraDAO).findAllValid().asScala.sortBy(d => d.name).foreach(e => {
            t.add(new TpElDropDown(e.uuid.toString, e.name))
          })
        })

      )

      JsonResult(resp)


    }
  }

  def getTipoCarta = Cached("list.tipocartas") {
    Action {

      val lista = new util.ArrayList[TpElDropDown]()
      val resp = new TpDropDown("uuid", "Tipo de Carta",

        (new util.ArrayList[TpElDropDown]() {
          t =>
          (new TipoCartaDAO).all().asScala.filter(d =>
            d.ativo.equals(true)).sortBy(d => d.name).foreach(e => {
            t.add(new TpElDropDown(e.uuid.toString, e.name))
          })
        })

      )
      JsonResult(resp)

    }
  }

  def getConta = SecuredAction { implicit request =>
    JsonResult(new ListaContaBanco(new ContaBancoDAO()
      .findAllbyUser(_userdao.findbyemailandprovider(_user.get.email.get, _user.get.identityId.providerId))
      .asScala.toList.sortBy(t => t.created)
    ))
  }


  def getUF = Action {
    Redirect("/assets/App/Mockup/Estados.json")
  }

  def getBanco = Action {
    Redirect("/assets/App/Mockup/Bancos.json")
  }

  def getContemplacao = Action {
    Redirect("/assets/App/Mockup/Filtros/contemplacao.json")
  }


  def getEndereco(cep: String) = Action {
    JsonResult(CepService.buscaCEP(cep))
  }


}
