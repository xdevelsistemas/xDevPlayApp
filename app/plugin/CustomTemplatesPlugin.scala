package controllers.plugin

import play.api.mvc.{RequestHeader, Request}
import play.api.templates.{Txt,Html}
import securesocial.core.{SecureSocial, Identity, SecuredRequest, SocialUser}
import play.api.data.Form
import securesocial.controllers.Registration.RegistrationInfo
import securesocial.controllers.PasswordChange.ChangeInfo

import securesocial.controllers.TemplatesPlugin

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
    views.html.Custom.login(form, msg)
  }

  /**
   * Returns the html for the signup page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getSignUpPage[A](implicit request: Request[A], form: Form[RegistrationInfo], token: String): Html = {
    views.html.Custom.Registration.signUp(form, token)
  }

  /**
   * Returns the html for the start signup page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartSignUpPage[A](implicit request: Request[A], form: Form[String]): Html = {
    views.html.Custom.Registration.startSignUp(form)
  }

  /**
   * Returns the html for the reset password page
   *
   * @param request
   * @tparam A
   * @return
   */
  override def getStartResetPasswordPage[A](implicit request: Request[A], form: Form[String]): Html = {
    views.html.Custom.Registration.startResetPassword(form)
  }

  /**
   * Returns the html for the start reset page
   *
   * @param request
   * @tparam A
   * @return
   */
  def getResetPasswordPage[A](implicit request: Request[A], form: Form[(String, String)], token: String): Html = {
    views.html.Custom.Registration.resetPasswordPage(form, token)
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
     views.html.Proconsorcio.alterarsenha(form)

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
    views.html.Custom.mails.signUpEmail(token).body
  }
  */

  def getSignUpEmail(token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Custom.mails.signUpEmail(token)))
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
    views.html.Custom.mails.alreadyRegisteredEmail(user).body
  }
  */

  def getAlreadyRegisteredEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Custom.mails.alreadyRegisteredEmail(user)))
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
    views.html.Custom.mails.welcomeEmail(user).body
  }
  */
  def getWelcomeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Custom.mails.welcomeEmail(user)))
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
    views.html.Custom.mails.unknownEmailNotice(request).body
  }
  */

  def getUnknownEmailNotice()(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Custom.mails.unknownEmailNotice(request)))
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
    views.html.Custom.mails.passwordResetEmail(user, token).body
  }
  */
  def getSendPasswordResetEmail(user: Identity, token: String)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Custom.mails.passwordResetEmail(user, token)))
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
    views.html.Custom.mails.passwordChangedNotice(user).body
  }
  */
  def getPasswordChangedNoticeEmail(user: Identity)(implicit request: RequestHeader): (Option[Txt], Option[Html]) = {
    (None, Some(views.html.Custom.mails.passwordChangedNotice(user)))
  }


  def getNotAuthorizedPage[A](implicit request: Request[A]): Html = {
    views.html.Custom.notAuthorized()
  }

}