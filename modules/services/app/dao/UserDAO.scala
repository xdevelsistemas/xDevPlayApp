package dao

import scala.Option

import models.User


class UserDAO extends AbstractDAO[User](classOf[User]) {

  def create(i : securesocial.core.Identity) : User = {
    val o = new User()
    o.email = i.email.get
    o.realName =
      if (Option(i.fullName).isDefined)
        i.fullName
      else
      if (Option(i.firstName).isDefined)
        i.firstName
      else
        i.identityId.userId
    return o
  }

}
