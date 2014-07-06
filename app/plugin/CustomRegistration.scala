package plugin

/**
 * Created by claytonsantosdasilva on 01/07/14.
 */

import _root_.java.util.{Date, UUID}

import com.typesafe.plugin._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.data.Forms._
import play.api.data._
import play.api.data.validation.Constraint
import play.api.i18n.Messages
import play.api.mvc._
import play.api.templates.Html
import play.api.{Logger, Play}
import securesocial.controllers.ProviderController
import securesocial.controllers.Registration._
import securesocial.core._
import securesocial.core.providers.utils._
import securesocial.core.providers.{Token, UsernamePasswordProvider}
import scala.language.reflectiveCalls



/**
 * A controller to handle user registration.
 *
 */
object CustomRegistration extends Controller {

  val providerId = UsernamePasswordProvider.UsernamePassword
  val UserNameAlreadyTaken = "securesocial.signup.userNameAlreadyTaken"
  val PasswordsDoNotMatch = "securesocial.signup.passwordsDoNotMatch"
  val ThankYouCheckEmail = "securesocial.signup.thankYouCheckEmail"
  val InvalidLink = "securesocial.signup.invalidLink"
  val SignUpDone = "securesocial.signup.signUpDone"
  val PasswordUpdated = "securesocial.password.passwordUpdated"
  val ErrorUpdatingPassword = "securesocial.password.error"

  val UserName = "userName"
  val FirstName = "firstName"
  val LastName = "lastName"
  val Password = "password"
  val Password1 = "password1"
  val Password2 = "password2"
  val Email = "email"
  val Success = "success"
  val Error = "error"

  // campos personalizados
  val BirthDate = "birthDate"
  val NumCep = "numCep"
  val Uf = "uf"
  val Cidade = "cidade"
  val Bairro = "bairro"
  val Logradouro = "logradouro"
  val NumLogradouro = "numLogradouro"
  val Rg = "rg"
  //val UfRg = "ufRg"
  val DocFederal = "docFederal"
  val NumBanco = "numBanco"
  val NumAgencia = "numAgencia"
  //val DvAgencia = "dvAgencia"
  val NumConta = "numConta"
  //val DvConta = "dvConta"
  val NumCodigo = "numCodigo"
  val NumCodigo1 = "numCodigo1"
  val NumCodigo2 = "numCodigo2"


  case class RegistrationInfo( userName: Option[String],
                               firstName: String,
                               birthDate:String,
                               password: String,
                               numCep :Option[String],
                               uf:Option[String],
                               cidade:Option[String],
                               bairro:Option[String],
                               logradouro:Option[String],
                               numLogradouro:Option[String],
                               rg:Option[String],
                               //ufRg:Option[String],
                               docFederal:Option[String],
                               numBanco:Option[String],
                               numAgencia:Option[String],
                               //dvAgencia:Option[String],
                               numConta:Option[String],
                               //dvConta:Option[String],
                               numCodigo:String)

  val formWithUsername = Form[RegistrationInfo](
    mapping(
      UserName -> nonEmptyText.verifying(Messages(UserNameAlreadyTaken), userName => {
        UserService.find(IdentityId(userName, providerId)).isEmpty
      }),
      FirstName -> nonEmptyText,
      BirthDate -> nonEmptyText,
      (Password ->
        tuple(
          Password1 -> nonEmptyText.verifying(use[PasswordValidator].errorMessage,
            p => use[PasswordValidator].isValid(p)
          ),
          Password2 -> nonEmptyText
        ).verifying(Messages(PasswordsDoNotMatch), passwords => passwords._1 == passwords._2)
        ),

      NumCep -> nonEmptyText,
      Uf -> text ,
      Cidade -> text ,
      Bairro -> text ,
      Logradouro -> text ,
      NumLogradouro -> text ,
      Rg -> text ,
      //UfRg -> text ,
      DocFederal -> text ,
      NumBanco -> text ,
      NumAgencia -> text ,
      //DvAgencia -> text ,
      NumConta -> text ,
      //DvConta -> text ,
      (NumCodigo ->
        tuple(
          NumCodigo1 -> nonEmptyText.verifying(use[PasswordValidator].errorMessage,
            p => use[PasswordValidator].isValid(p)
          ),
          NumCodigo2 -> nonEmptyText
        ).verifying(Messages(PasswordsDoNotMatch), passwords => passwords._1 == passwords._2)
        )

    )
      // binding
      ((userName,
        firstName,
        birthDate,
        password,
        numCep,
        uf,
        cidade,
        bairro,
        logradouro,
        numLogradouro,
        rg,
        //ufRg,
        docFederal,
        numBanco,
        numAgencia,
        //dvAgencia,
        numConta,
        //dvConta,
        numCodigo

         ) => RegistrationInfo(
              Some(userName),
              firstName,
              birthDate,
              password._1,
              Some(numCep),
              Some(uf),
              Some(cidade),
              Some(bairro),
              Some(logradouro),
              Some(numLogradouro),
              Some(rg),
              //Some(ufRg),
              Some(docFederal),
              Some(numBanco),
              Some(numAgencia),
              //Some(dvAgencia),
              Some(numConta),
              //Some(dvConta),
              numCodigo._1
         )
      )
      // unbinding
      (info =>
        Some(
        info.userName.getOrElse(""),
        info.firstName,
        info.birthDate,
        ("", ""),
        info.numCep.getOrElse(""),
        info.uf.getOrElse(""),
        info.cidade.getOrElse(""),
        info.bairro.getOrElse(""),
        info.logradouro.getOrElse(""),
        info.numLogradouro.getOrElse(""),
        info.rg.getOrElse(""),
        //info.ufRg.getOrElse(""),
        info.docFederal.getOrElse(""),
        info.numBanco.getOrElse(""),
        info.numAgencia.getOrElse(""),
        //info.dvAgencia.getOrElse(""),
        info.numConta.getOrElse(""),
        //info.dvConta.getOrElse(""),
        ("", "")
      ))
  )


  val formWithoutUsername = Form[RegistrationInfo](
    mapping(
      FirstName -> nonEmptyText,
      BirthDate -> nonEmptyText,
      (Password ->
        tuple(
          Password1 -> nonEmptyText.verifying(use[PasswordValidator].errorMessage,
            p => use[PasswordValidator].isValid(p)
          ),
          Password2 -> nonEmptyText
        ).verifying(Messages(PasswordsDoNotMatch), passwords => passwords._1 == passwords._2)
        ),

      NumCep -> text ,
      Uf -> text ,
      Cidade -> text ,
      Bairro -> text ,
      Logradouro -> text ,
      NumLogradouro -> text ,
      Rg -> text ,
      //UfRg -> text ,
      DocFederal -> text ,
      NumBanco -> text ,
      NumAgencia -> text ,
      //DvAgencia -> text ,
      NumConta -> text ,
      //DvConta -> text ,
      (NumCodigo ->
        tuple(
          NumCodigo1 -> nonEmptyText.verifying(use[PasswordValidator].errorMessage,
            p => use[PasswordValidator].isValid(p)
          ),
          NumCodigo2 -> nonEmptyText
        ).verifying(Messages(PasswordsDoNotMatch), passwords => passwords._1 == passwords._2)
        )
    )
      // binding
      ((firstName,
        birthDate,
        password,
        numCep,
        uf,
        cidade,
        bairro,
        logradouro,
        numLogradouro,
        rg,
        //ufRg,
        docFederal,
        numBanco,
        numAgencia,
        //dvAgencia,
        numConta,
        //dvConta,
        numCodigo

         ) => RegistrationInfo(
        None,
        firstName,
        birthDate,
        password._1,
        Some(numCep),
        Some(uf),
        Some(cidade),
        Some(bairro),
        Some(logradouro),
        Some(numLogradouro),
        Some(rg),
        //Some(ufRg),
        Some(docFederal),
        Some(numBanco),
        Some(numAgencia),
        //Some(dvAgencia),
        Some(numConta),
        //Some(dvConta),
        numCodigo._1
      )
        )
      // unbinding
      (info =>
        Some(
          info.firstName,
          info.birthDate,
          ("", ""),
          info.numCep.getOrElse(""),
          info.uf.getOrElse(""),
          info.cidade.getOrElse(""),
          info.bairro.getOrElse(""),
          info.logradouro.getOrElse(""),
          info.numLogradouro.getOrElse(""),
          info.rg.getOrElse(""),
          //info.ufRg.getOrElse(""),
          info.docFederal.getOrElse(""),
          info.numBanco.getOrElse(""),
          info.numAgencia.getOrElse(""),
          //info.dvAgencia.getOrElse(""),
          info.numConta.getOrElse(""),
          //info.dvConta.getOrElse(""),
          ("", "")
        ))
  )



  val form = if (UsernamePasswordProvider.withUserNameSupport) formWithUsername else formWithoutUsername


  private def createToken(email: String, isSignUp: Boolean): (String, Token) = {
    val uuid = UUID.randomUUID().toString
    val now = DateTime.now

    val token = Token(
      uuid, email,
      now,
      now.plusMinutes(TokenDuration),
      isSignUp = isSignUp
    )
    UserService.save(token)
    (uuid, token)
  }





  private def executeForToken(token: String, isSignUp: Boolean, f: Token => Result): Result = {
    UserService.findToken(token) match {
      case Some(t) if !t.isExpired && t.isSignUp == isSignUp => {
        f(t)
      }
      case _ => {
        val to = if (isSignUp) CustomRoutesHelper.startSignUp() else RoutesHelper.startResetPassword()
        Redirect(to).flashing(Error -> Messages(InvalidLink))
      }
    }
  }


  def startSignUp = Action { implicit request =>
    if (registrationEnabled) {
      if ( SecureSocial.enableRefererAsOriginalUrl ) {
        SecureSocial.withRefererAsOriginalUrl(Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.Registration.startSignUp(startForm), "Registrar", null)))
      } else {
        Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.Registration.startSignUp(startForm), "Registrar", null))
      }
    }
    else NotFound(views.html.defaultpages.notFound.render(request, None))
  }



  def handleStartSignUp = Action { implicit request =>
    if (registrationEnabled) {
      startForm.bindFromRequest.fold (
        errors => {
          BadRequest(views.html.Custom.Registration.startSignUp(errors))
        },
        email => {
          // check if there is already an account for this email address
          UserService.findByEmailAndProvider(email, UsernamePasswordProvider.UsernamePassword) match {
            case Some(user) => {
              // user signed up already, send an email offering to login/recover password
              Mailer.sendAlreadyRegisteredEmail(user)
            }
            case None => {
              val token = createToken(email, isSignUp = true)
              Mailer.sendSignUpEmail(email, token._1)
            }
          }
          Redirect(onHandleStartSignUpGoTo).flashing(Success -> Messages(ThankYouCheckEmail), Email -> email)
        }
      )
    }
    else NotFound(views.html.defaultpages.notFound.render(request, None))
  }

  /**
   * Renders the sign up page
   * @return
   */
  def signUp(token: String) = Action { implicit request =>
    if (registrationEnabled) {
      if (Logger.isDebugEnabled) {
        Logger.debug("[securesocial] trying sign up with token %s".format(token))
      }
      executeForToken(token, true, { _ =>
        Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.Registration.signUp(form,token), "Login", null))
      })
    }
    else NotFound(views.html.defaultpages.notFound.render(request, None))
  }


  /**
   * Handles posts from the sign up page
   */
  def handleSignUp(token: String) = Action { implicit request =>
    if (registrationEnabled) {
      executeForToken(token, true, { t =>
        form.bindFromRequest.fold(
          errors => {
            if (Logger.isDebugEnabled) {
              Logger.debug("[securesocial] errors " + errors)
            }
            BadRequest(views.html.Proconsorcio.Registration.signUp(errors, t.uuid))
          },
          info => {
            val id = if (UsernamePasswordProvider.withUserNameSupport) info.userName.get else t.email
            val identityId = IdentityId(id, providerId)
            val user = SocialUser(
              identityId,
              info.firstName,
              "",
              "%s".format(info.firstName),
              Some(t.email),
              GravatarHelper.avatarFor(t.email),
              AuthenticationMethod.UserPassword,
              passwordInfo = Some(Registry.hashers.currentHasher.hash(info.password))
            )
            val saved = UserService.save(user)

            //todo se nao funcionar o preeenchimento dos usuários irei colocar aqui as persistencias
            //User.completarCadastro(mail,form.get)


            UserService.deleteToken(t.uuid)
            if (UsernamePasswordProvider.sendWelcomeEmail) {
              Mailer.sendWelcomeEmail(saved)
            }


            val eventSession = Events.fire(new SignUpEvent(user)).getOrElse(session)
            if (UsernamePasswordProvider.signupSkipLogin) {
              ProviderController.completeAuthentication(user, eventSession).flashing(Success -> Messages(SignUpDone))
            } else {
              Redirect(onHandleSignUpGoTo).flashing(Success -> Messages(SignUpDone)).withSession(eventSession)
            }
          }
        )
      })
    }
    else NotFound(views.html.defaultpages.notFound.render(request, None))
  }


}

object CustomRoutesHelper {


  lazy val rr = play.Play.application().classloader().loadClass("plugin.ReverseCustomRegistration")

  lazy val registrationMethods = rr.newInstance().asInstanceOf[ {
    def handleSignUp(token: String) : Call
    def signUp(token: String): Call
    def handleStartSignUp(): Call
    def startSignUp(): Call

  }]


  def handleSignUp(token: String) = registrationMethods.handleSignUp(token)

  def signUp(token: String) = registrationMethods.signUp(token)

  def handleStartSignUp() = registrationMethods.handleStartSignUp()

  def startSignUp() = registrationMethods.startSignUp()


}



