package dao

import securesocial.core.AuthenticationMethod
import securesocial.core.IdentityId
import securesocial.core.OAuth1Info
import securesocial.core.OAuth2Info
import securesocial.core.PasswordInfo


/**
 * This trait provides access to the User associated to a certain Identity
 */
trait UserIdentity extends securesocial.core.Identity {

  val identity : models.Identity

  def user     : models.User = identity.user


  //
  // implements securesocial.core.Identity
  //

  override def identityId: IdentityId = IdentityId(identity.username, identity.provider)

  override def firstName: String = identity.firstName

  override def lastName: String  = identity.lastName

  override def fullName: String  = identity.fullName

  override def email: Option[String]     = Some(identity.email)

  override def avatarUrl: Option[String] = if (identity.hasAvatarUrl) Option(identity.avatarUrl) else None

  override def authMethod: AuthenticationMethod = AuthenticationMethod(identity.authMethod)

  override def oAuth1Info: Option[OAuth1Info] =
    if (identity.hasOAuth1Info)
      Option(OAuth1Info(identity.oAuth1InfoToken, identity.oAuth1InfoSecret))
    else
      None

  override def oAuth2Info: Option[OAuth2Info] =
    if (identity.hasOAuth2Info)
      Option(OAuth2Info(
        identity.oAuth2InfoAccessToken,
        Option(identity.oAuth2InfoTokenType),
        if (identity.oAuth2InfoExpiresIn <= 0) None else Option(identity.oAuth2InfoExpiresIn),
        Option(identity.oAuth2InfoRefreshToken)))
    else
      None

  override def passwordInfo: Option[PasswordInfo] =
    if (identity.hasPasswordInfo)
      Option(PasswordInfo(
        identity.passwordInfoHasher,
        identity.passwordInfoPassword,
        Option(identity.passwordInfoSalt)))
    else
      None

}
