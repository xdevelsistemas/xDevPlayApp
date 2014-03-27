package common

import securesocial.core.{Identity, Authorization}


/**
 * Checks authenticated provider
 */
case class WithProvider(provider: String) extends Authorization {

  def isAuthorized(user: Identity) = {
    user.identityId.providerId == provider
  }

}
