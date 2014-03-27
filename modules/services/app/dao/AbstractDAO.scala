package dao

import java.util.UUID

import scala.collection.JavaConversions._

import play.db.ebean.Model.Finder

import models.AbstractModel



//implicit def javaList2Seq[T](javaList: java.util.List[T]) : Seq[T] =
//    new BufferWrapper[T]() { def underlying = javaList }


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


/*
package dao;

import models.AbstractModel;
import play.db.ebean.Model;

import java.util.List;
import java.util.UUID;

public abstract class AbstractDAO<T extends AbstractModel> {

    private Class<T> classType;

    public Model.Finder<UUID, T> find;

    public AbstractDAO(Class<T> typeParameterClass) {
        classType = typeParameterClass;
        find = new Model.Finder<UUID, T>(UUID.class, classType);
    }

    public scala.Option<T> findOne(UUID uuid) {
        return scala.Option.apply(find
                .where()
                .eq("uuid", uuid)
                .findUnique());
    }

    public <A> scala.Option<T> findOne(String field, A value) {
        return scala.Option.apply(find
                .where()
                .eq(field, value)
                .findUnique());
    }

    public <A> List<T> findMany(String field, A value) {
        return find
                .where()
                .eq(field, value)
                .findList();
    }

    public List<T> all() {
        return find
                .where()
                .findList();
    }

    public T save(T obj) {
        if (obj.uuid != null) {
            obj.update();
        } else {
            obj.save();
        }
        return obj;
    }

    public void delete(UUID uuid) {
        find.byId(uuid).delete();
    }

}
*/
