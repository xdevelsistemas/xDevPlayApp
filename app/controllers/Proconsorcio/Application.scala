package controllers.Proconsorcio


import com.typesafe.plugin._
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.libs.json._
import java.text.{SimpleDateFormat}
import br.com.republicavirtual.{CepServiceVO, CepService}
import controllers.xDevController
import dao.{UserDAO}
import models.Cadastro.{RegistrationObjects, AlterarDadosInfo}
import models.User
import play.api.data._
import play.api.mvc.{Action}
import play.db.jpa.JPA
import play.libs.F
import securesocial.controllers.Registration
import securesocial.core.Identity
import securesocial.core.providers.utils.PasswordValidator
import play.api.Play.current


/**
 * Created by claytonsantosdasilva on 10/07/14.
 */
object Application extends xDevController{
  val NumCodigo = "numCodigo"
  val InvalidPasswordMessage = "securesocial.passwordChange.invalidPassword"
  val CodigoAtual = "codigoAtual"
  val Password1 = "password1"
  val Password2 = "password2"



  private def checkCurrentPassword(email: String , pass: String) = {
    _userdao.verificanumCodigoDigitado(email,pass)
  }




  def home  = UserAwareAction  { implicit request =>
    Ok(views.html.Proconsorcio.main.render(
      views.html.Proconsorcio.index.render(_userdao, _user)
      , "Pesquisa", _user,request))
  }


  def novacarta = SecuredAction { implicit request =>
     Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.novacarta.render,"Nova Carta", _user,request))
  }



  def detalhes(id: String) = Action { implicit request =>
    Ok(views.html.Proconsorcio.main(views.html.Proconsorcio.detalhes.apply(id)(_user,request))("Pesquisa",_user,request))
  }


  def pesquisa(query: String) = Action { implicit request =>
     Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.pesquisa.render(_userdao, _user, query), "Pesquisa", _user,request))
  }

  def pesquisa_clean =  Action { implicit request =>
     Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.pesquisa.render(_userdao, _user, ""), "Pesquisa", _user,request))
  }

  def simulador = Action { implicit request =>
     Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.simulador.render(), "Simulador", _user,request))
  }


  def dadoscadastrais = SecuredAction { implicit request =>
    val usuario: User = new UserDAO().findOne("email", _user.get.email.get)
    val regdata: AlterarDadosInfo = new AlterarDadosInfo(
      usuario.realName,
      if (usuario.get_birthDate == null) (new String("")) else (new SimpleDateFormat("dd/MM/yyyy")).format(usuario.get_birthDate),
      (new Some[String](if (usuario.get_numCep == null) (new String("")) else usuario.get_numCep)),
      if (usuario.get_siglaUf == null) (new String("")) else usuario.get_siglaUf,
      if (usuario.get_nomeCidade == null) (new String("")) else usuario.get_nomeCidade,
      if (usuario.get_nomeBairro == null) (new String("")) else usuario.get_nomeBairro,
      if (usuario.get_nomeLogradouro == null) (new String("")) else usuario.get_nomeLogradouro,
      if (usuario.get_numLogradouro == null) (new String("")) else usuario.get_numLogradouro,
      (new Some[String](if (usuario.get_numRg == null) (new String("")) else usuario.get_numRg)),
      if (usuario.get_numDocFederal == null) (new String("")) else usuario.get_numDocFederal)
    val userForm: Form[AlterarDadosInfo] = models.Cadastro.RegistrationObjects.formAlterarDados.fill(regdata)


    Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.dadoscadastrais.render(userForm, "", request), "Dados Cadastrais", _user,request))
  }


  def escritorio  = SecuredAction { implicit request =>
    Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.escritorio.render, "Escritório Online", _user, request))
  }

  def faleconosco = Action { implicit request =>
    Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.faleconosco.render, "Fale Conosco", _user, request))
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
        play.api.mvc.Results.BadRequest(views.html.Proconsorcio.main.render(views.html.Proconsorcio.dadoscadastrais(errors,""), "Dados Cadastrais", _user,request))

      },

      success => {

        //TODO encapsular persistencias
        JPA.withTransaction("default", false, new F.Function0[Void] {
          def apply: Void = {
            _userdao.alterarCadastro(((RegistrationObjects.formAlterarDados)bindFromRequest()).value,_user.get.email.get)
            return null
          }
        })

        play.api.mvc.Results.Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.dadoscadastrais((RegistrationObjects.formAlterarDados)bindFromRequest(),"Dados Alterados com Sucesso!"), "Dados Cadastrais",_user,request))
      }

    )


  }

  def alterarcodigo = SecuredAction { implicit request =>

    Ok(views.html.Proconsorcio.main.render(
      views.html.Proconsorcio.alterarcodigo(RegistrationObjects.formAlterarCodigo,"")
      , "Alterar Codigo", _user,request))
  }

  def handleAlterarCodigo = SecuredAction { implicit request =>


    val formAlterarCodigo =
      Form[models.Cadastro.AlteraCodigoInfo](
        mapping(
          CodigoAtual -> text.verifying(
            Messages(InvalidPasswordMessage), checkCurrentPassword(_user.get.email.get,_)),
          (NumCodigo ->
            tuple(
              Password1 -> nonEmptyText.verifying(use[PasswordValidator].errorMessage,
                p => use[PasswordValidator].isValid(p)),
              Password2 -> nonEmptyText
            ).verifying(Messages(Registration.PasswordsDoNotMatch), passwords => passwords._1 == passwords._2)
            )
        )
        ((codigoAtual, numCodigo) => models.Cadastro.AlteraCodigoInfo(Some(codigoAtual), numCodigo._1)
          )
          ((changeInfo: models.Cadastro.AlteraCodigoInfo) => Some("", ("", "")))
      )

    formAlterarCodigo.bindFromRequest.fold (

      errors => {
        BadRequest(views.html.Proconsorcio.main.render(views.html.Proconsorcio.alterarcodigo(errors,""), "Alterar Código de Compra/Venda", _user,request))

      },

      success => {
        //TODO encapsular persistencias
        JPA.withTransaction("default", false, new F.Function0[Void] {
          def apply: Void = {
            _userdao.AlteranumCodigo(_user.get.email.get, formAlterarCodigo.bindFromRequest().value.get.numCodigo)
            return null
          }
        })
        Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.alterarcodigo(RegistrationObjects.formAlterarCodigo,"Código Alterado com Sucesso!"), "Alterar Código de Compra/Venda", _user,request))
      }

    )


  }









}
