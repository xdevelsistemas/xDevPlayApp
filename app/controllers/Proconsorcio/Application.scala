package controllers.Proconsorcio





import models.Boleto.Boletos
import models.Contato.Contatoinfo
import play.api.Play
import play.api.data.Forms._
import play.api.i18n.Messages
import java.text.SimpleDateFormat
import controllers.xDevController
import dao.{IdentityDAO}
import models.Cadastro.{RegistrationObjects, AlterarDadosInfo}
import models.User
import play.api.data._
import play.api.mvc.Action
import play.db.jpa.JPA
import play.libs.F
import securesocial.controllers.Registration
import securesocial.core.providers.utils.PasswordValidator
import play.api.Play.current
import com.typesafe.plugin._
import scala.collection.JavaConverters._


/**
 * Created by claytonsantosdasilva on 10/07/14.
 *
 *
 */
object Application extends xDevController {
  val NumCodigo = "numCodigo"
  val InvalidPasswordMessage = "securesocial.passwordChange.invalidPassword"
  val CodigoAtual = "codigoAtual"
  val Password1 = "password1"
  val Password2 = "password2"


  private def checkCurrentPassword(email: String, providerId: String, pass: String) = {
    _userdao.verificanumCodigoDigitado(email, providerId, pass,false)
  }



  def home = UserAwareAction { implicit request =>
    Ok(views.html.App.main.render(
      views.html.Proconsorcio.index.render(_userdao, _user,request)
      , "Pesquisa", _user, request))
  }



  def novacarta = SecuredAction { implicit request =>

    if(_user.nonEmpty && (!_userdao.verificaCadastro(_user.get.email.get, _user.get.identityId.providerId))){
      Redirect(routes.Application.dadoscadastrais())
    }else{
      Ok(views.html.App.main.render(views.html.Proconsorcio.novacarta.render, "Nova Carta", _user, request))
    }

  }


  def detalhes = Action { implicit request =>

    Ok(views.html.App.main(views.html.Proconsorcio.detalhes(_user, request))("Pesquisa", _user, request))
  }


  def pesquisa(query: String) = Action { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.pesquisa.render(_userdao, _user, query,request,"",""), "Pesquisa", _user, request))


  }

  def pesquisa_clean = Action { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.pesquisa.render(_userdao, _user, "",request,"",""), "Pesquisa", _user, request))
  }

  def simulador = Action { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.simulador.render(), "Simulador", _user, request))
  }


  def dadoscadastrais = SecuredAction { implicit request =>
    val usuario: User = (new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user()
    val regdata: AlterarDadosInfo = new AlterarDadosInfo(
      usuario.realName,
      if (usuario.birthDate == null) new String("") else new SimpleDateFormat("dd/MM/yyyy").format(usuario.birthDate),
      new Some[String](if (usuario.numCep == null) new String("") else usuario.numCep),
      if (usuario.siglaUf == null) new String("") else usuario.siglaUf,
      if (usuario.nomeCidade == null) new String("") else usuario.nomeCidade,
      if (usuario.nomeBairro == null) new String("") else usuario.nomeBairro,
      if (usuario.nomeLogradouro == null) new String("") else usuario.nomeLogradouro,
      if (usuario.numLogradouro == null) new String("") else usuario.numLogradouro,
      new Some[String](if (usuario.numRg == null) new String("") else usuario.numRg),
      if (usuario.numDocFederal == null) new String("") else usuario.numDocFederal)
    val userForm: Form[AlterarDadosInfo] = models.Cadastro.RegistrationObjects.formAlterarDados.fill(regdata)


    Ok(views.html.App.main.render(views.html.Proconsorcio.dadoscadastrais.render(userForm, "", _user, _userdao, request), "Dados Cadastrais", _user, request))
  }


  def escritorio = SecuredAction { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.escritorio(_userdao, _user), "Escritório Online", _user, request))
  }

  def contas = SecuredAction { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.contas.render, "Minhas Contas Bancárias", _user, request))
  }

  def faleconosco = Action { implicit request =>

    if ( _user != null && _user.isDefined) {
      val usuario: User = (new IdentityDAO).findOneByEmailAndProvider(_user.get.email.get, _user.get.identityId.providerId).user()
      val regdata: Contatoinfo = new Contatoinfo(
        usuario.email,
        usuario.realName,
        "",
        "")
      val userForm: Form[Contatoinfo] = models.Contato.ContatoinfoObject.formContatoinfo.fill(regdata)
      Ok(views.html.App.main.render(views.html.Proconsorcio.faleconosco(userForm,_user ,"" , ""), "Fale Conosco", _user, request))
    }else{
      val regdata: Contatoinfo = new Contatoinfo(
        "",
        "",
        "",
        "")
      val userForm: Form[Contatoinfo] = models.Contato.ContatoinfoObject.formContatoinfo.fill(regdata)
      Ok(views.html.App.main.render(views.html.Proconsorcio.faleconosco(userForm,_user , "",""), "Fale Conosco", _user, request))
    }


  }

  def handlefaleConosco = Action { implicit request =>

    models.Contato.ContatoinfoObject.formContatoinfo.bindFromRequest.fold(
      errors => {


        BadRequest(views.html.App.main.render(views.html.Proconsorcio.faleconosco(errors, _user ,"",""), "Fale Conosco", _user, request))

      },

      success => {

        try {

          val mail: MailerAPI = play.Play.application().plugin(classOf[MailerPlugin]).email
          mail.setSubject(success.about)
          mail.setRecipient(success.email)
          mail.setFrom("proconsorcio@proconsorcio.com.br")
          val body = views.html.Proconsorcio.mails.faleconoscoCliente.render(success.name,success.about,request).body
          mail.sendHtml(body)

          val mailadmin: MailerAPI = play.Play.application().plugin(classOf[MailerPlugin]).email
          mailadmin.setSubject("nova mensagem do fale conosco, assunto:" + success.about)
          // enviando a mensagem a todos os administradores
          (_userdao).findMany("isAdmin", true).asScala.map( t => mailadmin.setRecipient(t.email))
          val bodyadm = views.html.Proconsorcio.mails.faleconoscoGestor.render(success.name,success.about,success.email,success.message,request).body
          mailadmin.setFrom("proconsorcio@proconsorcio.com.br")
          mailadmin.sendHtml(bodyadm)

          //Ok(views.html.App.main.render(views.html.Proconsorcio.index.render(_userdao,_user,request, "Obrigado! Em breve iremos retorná-lo") , "Pesquisa", _user, request))
          //"Obrigado! Em breve iremos retorná-lo"
          Redirect(routes.Application.home()).flashing("xdevel.msg"->"Obrigado! Em breve iremos retorná-lo")

        }catch {
          case e: Exception => {
            Ok(views.html.App.main.render(views.html.Proconsorcio.faleconosco(models.Contato.ContatoinfoObject.formContatoinfo.bindFromRequest(),_user ,"","Tivemos problemas ao enviar sua mensagem, por favor tente mais tarde ou mande email para proconsorcio@proconsorcio.com.br"), "Fale Conosco", _user, request))
          }
        }


      }

    )


  }


  def handleDadosCadastrais = SecuredAction { implicit request =>

    RegistrationObjects.formAlterarDados.bindFromRequest.fold(
      errors => {
        play.api.mvc.Results.BadRequest(views.html.App.main.render(views.html.Proconsorcio.dadoscadastrais(errors, "" ,_user,  _userdao), "Dados Cadastrais", _user, request))

      },

      success => {

        JPA.withTransaction("default", false, new F.Function0[Unit] {
          def apply: Unit = {
            _userdao.alterarCadastro(RegistrationObjects.formAlterarDados.bindFromRequest().value, _user.get.email.get, _user.get.identityId.providerId)
          }
        })

        //play.api.mvc.Results.Ok(views.html.App.main.render(views.html.Proconsorcio.index(_userdao,_user,request, "Dados Alterados com Sucesso!"), "Pesquisa", _user, request))
        //"Dados Alterados com Sucesso!"

        Redirect(routes.Application.home()).flashing("xdevel.msg"->"Dados Alterados com Sucesso!")
      }

    )


  }

  def alterarcodigo = SecuredAction { implicit request =>

    Ok(views.html.App.main.render(
      views.html.Proconsorcio.alterarcodigo(RegistrationObjects.formAlterarCodigo, "")
      , "Alterar Codigo", _user, request))
  }

  def handleAlterarCodigo = SecuredAction { implicit request =>


    val formAlterarCodigo =
      Form[models.Cadastro.AlteraCodigoInfo](
        mapping(
          CodigoAtual -> text.verifying(
            Messages(InvalidPasswordMessage), checkCurrentPassword(_user.get.email.get, _user.get.identityId.providerId, _)),
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

    formAlterarCodigo.bindFromRequest.fold(

      errors => {
        BadRequest(views.html.App.main.render(views.html.Proconsorcio.alterarcodigo(errors, ""), "Alterar Código de Compra/Venda", _user, request))

      },

      success => {

        JPA.withTransaction("default", false, new F.Function0[Unit] {
          def apply: Unit = {
            _userdao.AlteranumCodigo(_user.get.email.get, _user.get.identityId.providerId, formAlterarCodigo.bindFromRequest().value.get.numCodigo)

          }
        })
        //Ok(views.html.App.main.render(views.html.Proconsorcio.index.render(_userdao,_user,request, "Código Alterado com Sucesso!"), "Pesquisa", _user, request))
        //request.body = "Código Alterado com Sucesso!"

        Redirect(routes.Application.home()).flashing("xdevel.msg"->"Código Alterado com Sucesso!")
      }

    )


  }





  def boleto(id :String) = SecuredAction { implicit request =>

    implicit val userdao = _userdao
    implicit val user = _user
    val userCarta = if(user.isDefined && !user.isEmpty){(new IdentityDAO).findOneByEmailAndProvider(user.get.email.get, user.get.identityId.providerId).user()}else{null}
    val caminho = Play.application.path.getPath + "/public/Boletos"
    val arquivo = Boletos.geraBoleto(id,userCarta,caminho)

    if (arquivo.isEmpty){
      Redirect(routes.Application.home()).flashing("xdevel.msg.error"->"Não existe Boleto para a Carta Solicitada!")
    }else{
      Ok(views.html.App.main.render(views.html.Proconsorcio.boleto(arquivo), "Pagar Boleto", _user, request))
    }

  }


}
