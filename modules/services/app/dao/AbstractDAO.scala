package dao

import java.util.UUID

import scala.collection.JavaConversions._

import play.db.ebean.Model.Finder

import models.AbstractModel


class AbstractDAO[T <: AbstractModel](val classType: Class[T]) {

  val find: Finder[UUID, T] = new Finder(classOf[UUID], classType)

  def findOne(uuid: UUID) : Option[T] =
    Option(
      find
        .where()
        .eq("uuid", uuid)
        .findUnique())

  def findOne[A](field: String, value: A) : Option[T] =
    Option(
      find
        .where()
        .eq(field, value)
        .findUnique())

  def findMany[A](field: String, value: A) : Iterable[T] =
    find
      .where()
      .eq(field, value)
      .findList()

  def all() : Iterable[T] =
    find
      .where()
      .findList()


  def save(o: T) : T = {
    if (o.uuid != null)
      o.update()
    else
      o.save()
    return o
  }

  def delete(uuid: UUID) = find.byId(uuid).delete()

}
