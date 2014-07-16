package plugin

import play.api.data.Form
import play.api.mvc.{Request, RequestHeader}
import play.api.templates.{Html, Txt}
import securesocial.controllers.PasswordChange.ChangeInfo
import securesocial.controllers.TemplatesPlugin
import securesocial.core.{Identity, SecureSocial, SecuredRequest}

class CustomTemplatesPlugin(application: play.Application) extends TemplatesPlugin
{


  implicit def user(implicit request: RequestHeader):Option[Identity] = {
    SecureSocial.currentUser
  }


 /**
   * Returns the html for the login page
   * @param request
   * @tparam A
   * @return
   */
  override def getLoginPage[A](implicit request: Request[A], form: Form[(String, String)],
                               msg: Option[String] = None): Html =
  {
    //views.html.Custom.login(form, msg)
    views.html.Proconsorcio.main.render(views.html.Proconsorcio.login(form,msg), "Login", _user,request)

  }

  /**
   * Returns the html for the signup page
   *
   * @param request
   * @tparam A
   * @return
   */


  override  def getSignUpPage[A](implicit request: Request[A], form: Form[securesocial.controllers.Registration.RegistrationInfo], token: String): Html = {
    //views.html.Custom.Registration.signUp(form, token)
    null
  }

  /**
   * Returns the html for the start signup page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html = {
    views.html.Proconsorcio.main.render(views.html.Proconsorcio.Registration.startSignUp(form), "Registrar", _user,request)
    //views.html.Custom.Registration.startSignUp(form)
  }

  /**
   * Returns the html for the reset password page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartResetPasswordPage[A](implicit request: Request[A], form: Form[String]): Html = {
    //views.html.Custom.Registration.startResetPassword(form)
    views.html.Proconsorcio.main.render(views.html.Proconsorcio.Registration.startResetPassword(form), "Resetar Senha", _user,request)
  }

  /**
   * Returns the html for the start reset page
   *
   * @param request
   * @tparam A
   * @return
   */
  def getResetPasswordPage[A](implicit request: Request[A], form: Form[(String, String)], token: String): Html = {
    views.html.Proconsorcio.Registration.resetPasswordPage(form, token)
  }

   /**
   * Returns the html for the change password page
   *
   * @param request
   * @param form
   * @tparam A
   * @return
   */
  def getPasswordChangePage[A](implicit request: SecuredRequest[A], form: Form[ChangeInfo]): Html = {

     implicit def title = { "Alterar Senha" }

     views.html.Proconsorcio.main(views.html.Proconsorcio.alterarsenha(form))
  }

  /**
   * Returns the email sent when a user starts the sign up process
   *
   * @param token the token used to identify the request
   * @param request the current http request
   * @return a String with the html code for the email
   */

   /*
  def getSignUpEmail(token: String)(implicit request: play.api.mvc.RequestHeader): String = {
    views.html.Proconsorcio.mails.Registration.signUpEmail(token).body
  }
  */

  def getSignUpEmail(token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Proconsorcio.mails.Registration.signUpEmail(token)))
  }

  /**
   * Returns the email sent when the user is already registered
   *
   * @param user the user
   * @param request the current request
   * @return a String with the html code for the email
   */

  /* 
  def getAlreadyRegisteredEmail(user: SocialUser)(implicit request: play.api.mvc.RequestHeader): String = {
    views.html.Proconsorcio.mails.Registration.alreadyRegisteredEmail(user).body
  }
  */

  def getAlreadyRegisteredEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Proconsorcio.mails.Registration.alreadyRegisteredEmail(user)))
  }


  /**
   * Returns the welcome email sent when the user finished the sign up process
   *
   * @param user the user
   * @param request the current request
   * @return a String with the html code for the email
   */
  /* 
  def getWelcomeEmail(user: SocialUser)(implicit request: play.api.mvc.RequestHeader): String = {
    views.html.Proconsorcio.mails.Registration.welcomeEmail(user).body
  }
  */
  def getWelcomeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Proconsorcio.mails.Registration.welcomeEmail(user)))
  }

  /**
   * Returns the email sent when a user tries to reset the password but there is no account for
   * that email address in the system
   *
   * @param request the current request
   * @return a String with the html code for the email
   */
  /*
  def getUnknownEmailNotice()(implicit request: play.api.mvc.RequestHeader): String = {
    views.html.Proconsorcio.mails.Registration.unknownEmailNotice(request).body
  }
  */

  def getUnknownEmailNotice()(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Proconsorcio.mails.Registration.unknownEmailNotice(request)))
  }

  /**
   * Returns the email sent to the user to reset the password
   *
   * @param user the user
   * @param token the token used to identify the request
   * @param request the current http request
   * @return a String with the html code for the email
   */
   /*
  def getSendPasswordResetEmail(user: SocialUser, token: String)(implicit request: play.api.mvc.RequestHeader): String = {
    views.html.Proconsorcio.mails.Registration.passwordResetEmail(user, token).body
  }
  */
  def getSendPasswordResetEmail(user: Identity, token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Proconsorcio.mails.Registration.passwordResetEmail(user, token)))
  }


  /**
   * Returns the email sent as a confirmation of a password change
   *
   * @param user the user
   * @param request the current http request
   * @return a String with the html code for the email
   */
  /* 
  def getPasswordChangedNoticeEmail(user: SocialUser)(implicit request: play.api.mvc.RequestHeader): String = {
    views.html.Proconsorcio.mails.Registration.passwordChangedNotice(user).body
  }
  */
  def getPasswordChangedNoticeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Proconsorcio.mails.Registration.passwordChangedNotice(user)))
  }


  def getNotAuthorizedPage[A](implicit request: Request[A]): Html = {
    views.html.Custom.notAuthorized()
  }

  private def _user(implicit request: RequestHeader): Option[Identity] = {
    return SecureSocial.currentUser
  }

}