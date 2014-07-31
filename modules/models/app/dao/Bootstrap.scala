package dao

import java.util
import java.util.Map
import javax.persistence.EntityManager
import models.Proconsorcio.{Administradora, TipoCarta}
import models.{Identity, User}
import scala.collection.JavaConversions._

import play.db.jpa.JPA
import play.libs.{Yaml, F}

/**
 * Created by claytonsantosdasilva on 29/07/14.
 */
object Bootstrap {
  def startUp: Int = {
    return startUp("load-data.yml")
  }

  def fakeStartUp: Int = {
    return startUp("load-data.yml")
  }

  private def startUp(yaml: String): Int = {
    return processYml(Yaml.load(yaml).asInstanceOf[Map[String, List[Object]]])
  }

  def processYml(all: Map[String, List[Object]]): Int =
  {



    JPA.withTransaction("default", false, new F.Function0[Integer] {
      def apply: Integer = {
        val em: EntityManager = JPA.em

        def users: util.ArrayList[User] = all.get("users").asInstanceOf[util.ArrayList[User]]
        def identities: util.ArrayList[Identity] = all.get("identities").asInstanceOf[util.ArrayList[Identity]]
        def tiposCartas: util.ArrayList[TipoCarta] = all.get("tiposcartas").asInstanceOf[util.ArrayList[TipoCarta]]
        def administradoras: util.ArrayList[Administradora] = all.get("administradoras").asInstanceOf[util.ArrayList[Administradora]]


        val count: Int = 0
        val identityDAO: IdentityDAO = new IdentityDAO
        val tipoCartaDAO: TipoCartaDAO = new TipoCartaDAO
        val admnistradoraDAO: AdministradoraDAO = new AdministradoraDAO


        if (identities != null) {
          identities.filter(d =>
            ((identityDAO.findOneByEmailAndProviderIdentity(d.email, d.provider) == null))).foreach(e => {
            em.persist(e)
          })
        }

        if (tiposCartas != null) {
          tiposCartas.filter(d =>
            (tipoCartaDAO.findByName(d.name) == null)).foreach(e => {
            em.persist(e)
          })
        }

        if (administradoras != null) {
          administradoras.filter(d =>
            (admnistradoraDAO.findByName(d.name) == null)).foreach(e => {
            em.persist(e)
          })
        }

        return count;

      }
    })




  }



}



