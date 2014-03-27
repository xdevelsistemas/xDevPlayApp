package services

import play.api.{Logger, Application}

import securesocial.core._
import securesocial.core.providers.Token
import securesocial.core.{Identity,IdentityId}

import dao.DAO.identityDAO
import models.User


class AuthenticationService(application: Application) extends UserServicePlugin(application) {

  val logger = Logger("services.UserService")

  //
  // Identity management
  //

  def find(identity: IdentityId): Option[Identity] = {
    if ( logger.isDebugEnabled )
      logger.debug("Identity: find %s".format(identity))

    Option(identityDAO.findOneByIdentityId(identity))
  }

  def findByEmailAndProvider(email: String, provider: String): Option[Identity] = {
    if ( logger.isDebugEnabled )
      logger.debug("Identity: find %s [%s]".format(email, provider))

    Option(identityDAO.findOneByEmailAndProvider(email, provider))
  }

  def findByEmail(email: Option[String]): Iterable[Identity] = {
    if ( logger.isDebugEnabled )
      logger.debug("Identity: find %s".format(email))
    email match {
      case Some(e) =>
        identityDAO.findManyByEmail(e)
      case None =>
        Seq()
    }
  }

  def save(identity: Identity): Identity = {
    if ( logger.isDebugEnabled )
      logger.debug("Identity: save %s".format(identity.identityId))

    // first see if there is an Identity with this IdentityId already.
    val maybeIdentity = Option(identityDAO.findOneByIdentityId(identity.identityId))
    // create and save new models.Identity
    maybeIdentity match {
      case Some(existing) =>
        identity
      case None =>
        identityDAO.create(identity)
    }
  }

  def link(current: Identity, to: Identity) {
    if ( logger.isDebugEnabled )
      logger.debug("Identity: link current %s to %s".format(current.identityId, to.identityId))

    // first see if there is an Identity with this IdentityId already. It should exist !!!
    val maybeIdentity = Option(identityDAO.findOneByIdentityId(current.identityId))
    // create and save new models.Identity pointing to same user from existing Identity
    maybeIdentity match {
      case Some(existing) =>
        identityDAO.create(existing.user, to)
      case None =>
        throw new InternalError("this should never happen")
    }
  }


  //
  // Token management
  //

  private var tokens = Map[String, Token]()

  def findToken(token: String): Option[Token] = {
    tokens.get(token)
  }

  def save(token: Token) {
    tokens += (token.uuid -> token)
  }

  def deleteToken(uuid: String) {
    tokens -= uuid
  }

  def deleteTokens() {
    tokens = Map()
  }

  def deleteExpiredTokens() {
    tokens = tokens.filter(!_._2.isExpired)
  }

}
