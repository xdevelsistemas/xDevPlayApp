package controllers.Proconsorcio

import java.util
import java.util.UUID

import RestModels.ListaContaBanco
import _root_.util.{TpResponse, TpDropDown, TpElDropDown}
import controllers.Proconsorcio.Application._
import models.Cadastro.RegistrationObjects
import models.User
import play.api.cache.Cached
import play.api.libs.json.{Json, JsObject}
import play.api.mvc.Action
import play.db.jpa.JPA
import play.libs.F
import scala.collection.JavaConverters._
import br.com.republicavirtual.CepService
import controllers.xDevRestController
import dao.{IdentityDAO, ContaBancoDAO, TipoCartaDAO, AdministradoraDAO}
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
      val resp = new TpDropDown("uuid","Administradora",

        (new util.ArrayList[TpElDropDown]() { t=>
          (new AdministradoraDAO).findAllValid().asScala.sortBy(d => d.name).foreach(e => {
            t.add(new TpElDropDown(e.uuid.toString,e.name))
          })
        })

      )

      JsonResult(resp)


    }
  }

  def getTipoCarta = Cached("list.tipocartas") {
    Action {

      val lista = new util.ArrayList[TpElDropDown]()
      val resp = new TpDropDown("uuid","Tipo de Carta",

        (new util.ArrayList[TpElDropDown]() { t=>
          (new TipoCartaDAO).all().asScala.filter(d =>
            d.ativo.equals(true)).sortBy(d => d.name).foreach(e => {
            t.add(new TpElDropDown(e.uuid.toString,e.name))
          })
        })

      )
      JsonResult(resp)

    }
  }




  def getConta = SecuredAction{implicit request =>
    JsonResult(new ListaContaBanco(new ContaBancoDAO()
      .findAllbyUser((new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user())
      .asScala.toList.sortBy(t=> t.created)
    ))
  }

  def HandleinsertConta = SecuredAction(parse.json){implicit request =>
    var cta = new RestModels.ContaBancoForm
    val user: User = (new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user()
    val dao = new ContaBancoDAO

    JPA.withTransaction("default", false, new F.Function0[Unit] {
      def apply: Unit = {
        cta = dao.add(cta.read(request.body),user)
      }
    })

    if (cta.status.result.equals("0")){
      BadRequest(cta.serialize())
    }else {
      JsonResult(cta)
    }
  }

  def setPadrao(id : String) = SecuredAction{implicit request =>
    val dao = new ContaBancoDAO
    val uuid = UUID.fromString(id)
    val user: User = (new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user()
    val result = dao.setPadrao(uuid,user)

    if (result.result.equals("0")){
      BadRequest(result.serialize())
    }else {
      JsonResult(result)
    }

  }

  def remove(id : String) = SecuredAction{implicit request =>
    val uuid = UUID.fromString(id)
    val dao = new ContaBancoDAO
    var result = new  TpResponse("1","")

    JPA.withTransaction("default", false, new F.Function0[Unit] {
      def apply: Unit = {
        dao.delete(uuid)
      }
    })

    if (result.result.equals("0")){
      BadRequest(result.serialize())
    }else {
      JsonResult(result)
    }

  }



  def getUF = Action {
    Redirect("/assets/App/Mockup/Estados.json")
  }

  def getBanco = Action {
    Redirect("/assets/App/Mockup/Bancos.json")
  }



  def getEndereco(cep: String) = Action  {
    JsonResult(CepService.buscaCEP(cep))
  }


}
