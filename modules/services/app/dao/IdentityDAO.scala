package dao

import securesocial.core.IdentityId

import dao.DAO.userDAO


class IdentityDAO extends AbstractDAO[models.Identity](classOf[models.Identity]) {

  def create(i: securesocial.core.Identity) : UserIdentity = {
    val u = userDAO.create(i)
    create(u, i)
  }

  def create(u: models.User, i: securesocial.core.Identity) : UserIdentity = {
    val r = new models.Identity()
    r.user = u

    r.username = i.identityId.userId
    r.provider = i.identityId.providerId

    r.firstName = i.firstName
    r.lastName  = i.lastName
    r.fullName  = i.fullName

    r.email    = i.email.get

    r.hasAvatarUrl = i.avatarUrl.isDefined
    r.avatarUrl    = if (i.avatarUrl.isDefined) i.avatarUrl.get else null

    r.authMethod = i.authMethod.method

    r.hasOAuth1Info    = i.oAuth1Info.isDefined
    r.oAuth1InfoToken  = if (i.oAuth1Info.isDefined) i.oAuth1Info.get.token  else null
    r.oAuth1InfoSecret = if (i.oAuth1Info.isDefined) i.oAuth1Info.get.secret else null

    r.hasOAuth2Info          = i.oAuth2Info.isDefined
    r.oAuth2InfoAccessToken  = if (i.oAuth2Info.isDefined) i.oAuth2Info.get.accessToken                  else null
    r.oAuth2InfoTokenType    = if (i.oAuth2Info.isDefined) i.oAuth2Info.get.tokenType.getOrElse(null)    else null
    r.oAuth2InfoExpiresIn    = Int.box(if (i.oAuth2Info.isDefined) i.oAuth2Info.get.expiresIn.getOrElse(0) else 0)
    r.oAuth2InfoRefreshToken = if (i.oAuth2Info.isDefined) i.oAuth2Info.get.refreshToken.getOrElse(null) else null

    r.hasPasswordInfo      = i.passwordInfo.isDefined
    r.passwordInfoHasher   = if (i.passwordInfo.isDefined) i.passwordInfo.get.hasher               else null
    r.passwordInfoPassword = if (i.passwordInfo.isDefined) i.passwordInfo.get.password             else null
    r.passwordInfoSalt     = if (i.passwordInfo.isDefined) i.passwordInfo.get.salt.getOrElse(null) else null

    r.save()

    IdentityWrapper(r)
  }

  def findOneByIdentityId(identityId : IdentityId) : UserIdentity = {
    wrap(
      find.where()
        .eq("username", identityId.userId)
        .eq("provider", identityId.providerId)
        .findUnique())
  }

  def findOneByEmailAndProvider(email: String, provider: String) : UserIdentity = {
    wrap(
      find.where()
        .eq("email", email)
        .eq("provider", provider)
        .findUnique())
  }

  def findManyByEmail(email: String) : Iterable[UserIdentity] =
    findMany[String]("email", email)
      .map(
        p => wrap(p))


  def wrap(identity: models.Identity) : UserIdentity = {
    if (identity == null) null else IdentityWrapper(identity)
  }


  private case class IdentityWrapper(val identity: models.Identity) extends UserIdentity
  
}
