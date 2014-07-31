package models.test

import javax.persistence.EntityManager

import play.api.test._



import play.api.test.FakeApplication
import dao.{Bootstrap}
import play.db.jpa.JPA
import play.libs.F
import play.libs.F.Callback0

/**
 * Created by claytonsantosdasilva on 29/07/14.
 */
class BootStrapTest extends  PlaySpecification{
  "Teste de Carga de Dados iniciais" should {
    "Deve rodar sem problema" in {
      new WithApplication(FakeApplication(withoutPlugins = withoutPlugins)) {

//      JPA.withTransaction("default", false, new F.Function0[Integer] {
//        def apply: Integer = {
//          val em: EntityManager = JPA.em
//          return 0//Bootstrap.InitialLoadData("conf/initial-data.yml",em)
//        }
//      })


      }
    }
  }

  val withoutPlugins = Seq(
    "securesocial.core.DefaultAuthenticatorStore",
    "securesocial.core.DefaultIdGenerator",
    "securesocial.core.providers.utils.DefaultPasswordValidator",
    "securesocial.controllers.DefaultTemplatesPlugin",
    "services.AuthenticationService",
    "securesocial.core.providers.utils.BCryptPasswordHasher",
    "securesocial.core.providers.FacebookProvider",
    "securesocial.core.providers.GoogleProvider",
    "securesocial.core.providers.UsernamePasswordProvider",
    "services.AuthenticationListener"
  )

}


