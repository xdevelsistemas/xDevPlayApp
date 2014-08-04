package controllers.Proconsorcio

import java.util
import java.util.UUID
import javax.persistence.EntityTransaction

import models.Proconsorcio.RestModels.{CartaForm, ContaBancoForm, LstContaBanco}
import _root_.util.{TpResponse, TpDropDown, TpElDropDown}
import controllers.Proconsorcio.Application._
import models.Cadastro.RegistrationObjects
import models.Proconsorcio.ContaBanco
import models.User
import play.api.cache.Cached
import play.api.libs.json.{Json, JsObject}
import play.api.mvc.{SimpleResult, Action}
import play.db.jpa.JPA
import play.libs.F
import scala.collection.JavaConverters._
import br.com.republicavirtual.CepService
import controllers.xDevRestController
import dao._
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
    JsonResult(new LstContaBanco(new ContaBancoDAO()
      .findAllbyUser((new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user())
      .asScala.toList.sortBy(t => t.created)
    ))
  }

  def handleInsertConta = SecuredAction(parse.json) { implicit request =>

    try {

      val ctaBanco = (new ContaBancoForm).read(request.body)
      val user: User = (new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user()
      val dao = new ContaBancoDAO




      JPA.withTransaction("default", false, new F.Function0[SimpleResult] {
        def apply: SimpleResult = {
          val cta = dao.add(ctaBanco, user)

          if (cta.status.result.equals("0")) {
            BadRequest(cta.serialize())
          } else {
            JsonResult(cta)
          }
        }
      })

    } catch {
      case e: Exception => {
        BadRequest(new TpResponse("0", if(e.getMessage == null){"erro de execução"}else{e.getMessage}).serialize())
      }
    }

  }

  def handleSetPadrao(id: String) = SecuredAction { implicit request =>

    JPA.withTransaction("default", false, new F.Function0[SimpleResult] {
      def apply: SimpleResult = {
        try {

          val dao = new ContaBancoDAO
          val uuid = UUID.fromString(id)
          val user: User = (new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user()

          val result = dao.setPadrao(uuid, user)

          if (result.result.equals("0")) {
            BadRequest(result.serialize())
          } else {
            JsonResult(result)
          }

        } catch {
          case e: Exception => {
            BadRequest(new TpResponse("0", if(e.getMessage == null){"erro de execução"}else{e.getMessage}).serialize())
          }
        }


      }
    })


  }

  def handleRemove(id: String) = SecuredAction { implicit request =>

    try {
      val uuid = UUID.fromString(id)
      val dao = new ContaBancoDAO
      val result = new TpResponse("1", "")
      dao.delete(uuid)

      if (result.result.equals("0")) {
        BadRequest(result.serialize())
      } else {
        JsonResult(result)
      }

    } catch {
      case e: Exception => {
        BadRequest(new TpResponse("0", if(e.getMessage == null){"erro de execução"}else{e.getMessage}).serialize())
      }
    }


  }

  def getContemplacao = Action {
    Redirect("/assets/App/Mockup/Filtros/contemplacao.json")
  }

  def getOrdem = Action {
    Redirect("/assets/App/Mockup/Filtros/ordem.json")
  }

  def getOrdenador = Action {
    Redirect("/assets/App/Mockup/Filtros/ordenador.json")
  }

  def getPrazoRestante = Action {
    Redirect("/assets/App/Mockup/Filtros/prazo_restante.json")
  }


  def getUF = Action {
    Redirect("/assets/App/Mockup/Estados.json")
  }

  def getBanco = Action {
    Redirect("/assets/App/Mockup/Bancos.json")
  }


  def getEndereco(cep: String) = Action {
    JsonResult(CepService.buscaCEP(cep))
  }


  def handleInsertCarta = SecuredAction(parse.json) { implicit request =>

    try {

      val user: User = (new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user()
      val frm = (new CartaForm)
      val carta = frm.readAndValidate(request.body,user)
      val dao = new CartaDAOextend


      if (frm.status.result.equals("0"))
      {
        BadRequest(frm.serialize())
      }else{

        JPA.withTransaction("default", false, new F.Function0[SimpleResult] {
          def apply: SimpleResult = {
            carta.usuario = user
            val frm_novacarta = dao.add(carta, user)

            if (frm_novacarta.status.result.equals("0")) {
              BadRequest(frm_novacarta.serialize())
            } else {
              JsonResult(frm_novacarta)
            }
          }
        })

      }

    } catch {
      case e: Exception => {
        BadRequest(new TpResponse("0", if(e.getMessage == null){"erro de execução"}else{e.getMessage}).serialize())
      }
    }

  }

}
