package controllers.Proconsorcio


import javax.persistence.{EntityManager, EntityTransaction}

import com.typesafe.plugin._
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.libs.json._
import java.text.SimpleDateFormat
import br.com.republicavirtual.CepService
import controllers.xDevController
import dao.IdentityDAO
import models.Cadastro.{RegistrationObjects, AlterarDadosInfo}
import models.User
import play.api.data._
import play.api.mvc.Action
import play.libs.F
import securesocial.controllers.Registration
import securesocial.core.providers.utils.PasswordValidator
import play.api.Play.current
import play.db.jpa.JPA


/**
 * Created by claytonsantosdasilva on 10/07/14.
 * Controller responsável pelo processamento das páginas dentro da aplicação proconsórcio
 *
 * Desenvolvido pela xDevel Sistemas
 *
 */
object Application extends xDevController{
  val NumCodigo = "numCodigo"
  val InvalidPasswordMessage = "securesocial.passwordChange.invalidPassword"
  val CodigoAtual = "codigoAtual"
  val Password1 = "password1"
  val Password2 = "password2"



  private def checkCurrentPassword(email: String , providerId : String , pass: String) = {
    _userdao.verificanumCodigoDigitado(email,providerId,pass)
  }




  def home  = UserAwareAction  { implicit request =>
    Ok(views.html.App.main.render(
      views.html.Proconsorcio.index.render(_userdao, _user)
      , "Pesquisa", _user,request))
  }


  def novacarta = SecuredAction { implicit request =>
     Ok(views.html.App.main.render(views.html.Proconsorcio.novacarta.render,"Nova Carta", _user,request))
  }



  def detalhes(id: String) = Action { implicit request =>
    Ok(views.html.App.main(views.html.Proconsorcio.detalhes.apply(id)(_user,request))("Pesquisa",_user,request))
  }


  def pesquisa(query: String) = Action { implicit request =>
     Ok(views.html.App.main.render(views.html.Proconsorcio.pesquisa.render(_userdao, _user, query), "Pesquisa", _user,request))
  }

  def pesquisa_clean =  Action { implicit request =>
     Ok(views.html.App.main.render(views.html.Proconsorcio.pesquisa.render(_userdao, _user, ""), "Pesquisa", _user,request))
  }

  def simulador = Action { implicit request =>
     Ok(views.html.App.main.render(views.html.Proconsorcio.simulador.render(), "Simulador", _user,request))
  }


  def dadoscadastrais = SecuredAction { implicit request =>
    val usuario: User = (new IdentityDAO).findOneByEmailAndProvider( _user.get.email.get,_user.get.identityId.providerId).user()
    val regdata: AlterarDadosInfo = new AlterarDadosInfo(
      usuario.realName,
      if (usuario.birthDate == null) new String("") else new SimpleDateFormat("dd/MM/yyyy").format(usuario.birthDate),
      new Some[String](if (usuario.numCep == null || usuario.numCep.equals("") ) new String("") else usuario.numCep),
      if (usuario.siglaUf == null || usuario.siglaUf.equals("") )  new String("") else usuario.siglaUf,
      if (usuario.nomeCidade == null || usuario.nomeCidade.equals("") )  new String("") else usuario.nomeCidade,
      if (usuario.nomeBairro == null || usuario.nomeBairro.equals("") )  new String("") else usuario.nomeBairro,
      if (usuario.nomeLogradouro == null || usuario.nomeLogradouro.equals("") )  new String("") else usuario.nomeLogradouro,
      if (usuario.numLogradouro == null || usuario.numLogradouro.equals("") )  new String("") else usuario.numLogradouro,
      new Some[String](if (usuario.numRg == null || usuario.numRg.equals("") )  new String("") else usuario.numRg),
      if (usuario.numDocFederal == null || usuario.numDocFederal.equals("") )  new String("") else usuario.numDocFederal)
    val userForm: Form[AlterarDadosInfo] = models.Cadastro.RegistrationObjects.formAlterarDados.fill(regdata)


    Ok(views.html.App.main.render(views.html.Proconsorcio.dadoscadastrais.render(userForm, "", request), "Dados Cadastrais", _user,request))
  }


  def escritorio  = SecuredAction { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.escritorio.render, "Escritório Online", _user, request))
  }

  def faleconosco = Action { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.faleconosco.render, "Fale Conosco", _user, request))
  }





  def getEndereco(cep: String) = Action { implicit request =>

    val  result = CepService.buscaCEP(cep)
    val  json =

    Json.obj(
      "uf" -> result.getUf,
      "cidade" -> result.getCidade,
      "bairro" -> result.getBairro,
      "tipo_logradouro" -> result.getTipo_logradouro,
      "logradouro" -> result.getLogradouro,
      "resultado" -> result.getResultado,
      "resultado_txt" -> result.getResultado_txt
    )



    Ok(json)
  }

  def getUF = Action {
    Redirect("/assets/App/Mockup/Estados.json")
  }

  def getBanco = Action {
    Redirect("/assets/App/Mockup/Bancos.json")
  }


  def handleDadosCadastrais = SecuredAction { implicit request =>

    RegistrationObjects.formAlterarDados.bindFromRequest.fold (
      errors => {
        play.api.mvc.Results.BadRequest(views.html.App.main.render(views.html.Proconsorcio.dadoscadastrais(errors,""), "Dados Cadastrais", _user,request))

      },

      success => {
        JPA.withTransaction(new F.Function0[Void] {
          def apply: Unit = {
             _userdao.alterarCadastro(RegistrationObjects.formAlterarDados.bindFromRequest().value,_user.get.email.get, _user.get.identityId.providerId)
          }
        })

        play.api.mvc.Results.Ok(views.html.App.main.render(views.html.Proconsorcio.dadoscadastrais(RegistrationObjects.formAlterarDados.bindFromRequest(),"Dados Alterados com Sucesso!"), "Dados Cadastrais",_user,request))



      }

    )


  }

  def alterarcodigo = SecuredAction { implicit request =>

    Ok(views.html.App.main.render(
      views.html.Proconsorcio.alterarcodigo(RegistrationObjects.formAlterarCodigo,"")
      , "Alterar Codigo", _user,request))
  }

  def handleAlterarCodigo = SecuredAction { implicit request =>


    val formAlterarCodigo =
      Form[models.Cadastro.AlteraCodigoInfo](
        mapping(
          CodigoAtual -> text.verifying(
            Messages(InvalidPasswordMessage), checkCurrentPassword(_user.get.email.get,_user.get.identityId.providerId ,_)),
          NumCodigo ->
            tuple(
              Password1 -> nonEmptyText.verifying(use[PasswordValidator].errorMessage,
                p => use[PasswordValidator].isValid(p)),
              Password2 -> nonEmptyText
            ).verifying(Messages(Registration.PasswordsDoNotMatch), passwords => passwords._1 == passwords._2)

        )
        ((codigoAtual, numCodigo) => models.Cadastro.AlteraCodigoInfo(Some(codigoAtual), numCodigo._1)
          )
          ((changeInfo: models.Cadastro.AlteraCodigoInfo) => Some("", ("", "")))
      )

    formAlterarCodigo.bindFromRequest.fold (

      errors => {
        BadRequest(views.html.App.main.render(views.html.Proconsorcio.alterarcodigo(errors,""), "Alterar Código de Compra/Venda", _user,request))

      },

      success => {

        JPA.withTransaction(new F.Function0[Void] {
          def apply: Unit = {
            _userdao.AlteranumCodigo(_user.get.email.get,_user.get.identityId.providerId, formAlterarCodigo.bindFromRequest().value.get.numCodigo)

          }
        })
        Ok(views.html.App.main.render(views.html.Proconsorcio.alterarcodigo(RegistrationObjects.formAlterarCodigo,"Código Alterado com Sucesso!"), "Alterar Código de Compra/Venda", _user,request))
      }

    )


  }









}
