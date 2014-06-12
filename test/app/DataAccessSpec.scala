package app

import play.api.test._

import org.specs2.mock._

import play.api.test.FakeApplication
import dao.{IdentityDAO, UserDAO}
import play.db.jpa.JPA
import play.libs.F.Callback0

class DataAccessSpec extends PlaySpecification with Mockito with SpecSettings {

//  "IdentityDAO" should {
//    "be able to find Identity given its IdentityId" in {
//      new WithApplication(FakeApplication(withoutPlugins = withoutPlugins)) {
//        JPA.withTransaction(new Callback0 { def invoke() : Unit = {
//            val mockIdentityId   = mock[securesocial.core.IdentityId]
//            val mockAuthMethod   = mock[securesocial.core.AuthenticationMethod]
//            val mockOAuth1Info   = null
//            val mockOAuth2Info   = null
//            val mockPasswordInfo = mock[securesocial.core.PasswordInfo]
//            val mockIdentity     = mock[securesocial.core.Identity]
//
//            val userId     = "bob"
//            val providerId = "google"
//            val firstName  = "Robert"
//            val lastName   = "Smith"
//            val fullName   = "Robert Smith"
//            val method     = "userpass"
//            val email      = Option("bob@gmail.com")
//            val avatarUrl  = None
//            val hasher     = "plain"
//            val password   = "secret"
//            val salt       = None
//
//            mockIdentityId.userId     returns userId
//            mockIdentityId.providerId returns providerId
//
//            mockAuthMethod.method     returns method
//
//            mockPasswordInfo.hasher   returns hasher
//            mockPasswordInfo.password returns password
//            mockPasswordInfo.salt     returns salt
//
//            mockIdentity.identityId   returns mockIdentityId
//            mockIdentity.firstName    returns firstName
//            mockIdentity.lastName     returns lastName
//            mockIdentity.fullName     returns fullName
//            mockIdentity.email        returns email
//            mockIdentity.avatarUrl    returns avatarUrl
//            mockIdentity.authMethod   returns mockAuthMethod
//            mockIdentity.oAuth1Info   returns Option(mockOAuth1Info)
//            mockIdentity.oAuth2Info   returns Option(mockOAuth2Info)
//            mockIdentity.passwordInfo returns Option(mockPasswordInfo)
//
//            val userDAO = new UserDAO
//            val identityDAO = new IdentityDAO
//            val u = userDAO.create(mockIdentity, false)
//            val i = identityDAO.create(u, mockIdentity, true)
//
//            i.user                   must be equalTo u
//            i.identityId.userId      must be equalTo mockIdentityId.userId
//            i.identityId.providerId  must be equalTo mockIdentityId.providerId
//            i.firstName              must be equalTo firstName
//            i.lastName               must be equalTo lastName
//            i.fullName               must be equalTo fullName
//            i.email                  must be equalTo email
//            i.avatarUrl              must be equalTo avatarUrl
//
//            i.authMethod.method      must be equalTo method
//            i.oAuth1Info             must be equalTo None
//            i.oAuth2Info             must be equalTo None
//
//            i.passwordInfo.get.hasher   must be equalTo hasher
//            i.passwordInfo.get.password must be equalTo password
//            i.passwordInfo.get.salt     must be equalTo None
//
//            val persisted = identityDAO.findOneByIdentityId(mockIdentityId)
//            persisted.identityId.userId     must be equalTo userId
//            persisted.identityId.providerId must be equalTo providerId
//        }})
//      }
//    }
//  }
//
//  "IdentityDAO" should {
//    "be able to find Identity given (email, provider)" in {
//      new WithApplication(FakeApplication(withoutPlugins = withoutPlugins)) {
//        JPA.withTransaction(new Callback0 { def invoke() : Unit = {
//
//          val mockIdentityId = mock[securesocial.core.IdentityId]
//          val mockAuthMethod = mock[securesocial.core.AuthenticationMethod]
//          val mockOAuth1Info = null
//          val mockOAuth2Info = null
//          val mockPasswordInfo = mock[securesocial.core.PasswordInfo]
//          val mockIdentity = mock[securesocial.core.Identity]
//
//          val userId = "susan"
//          val providerId = "google"
//          val firstName = "Susan"
//          val lastName = "Smith"
//          val fullName = "Susan Smith"
//          val method = "userpass"
//          val email = Option("susan@gmail.com")
//          val avatarUrl = None
//          val hasher = "plain"
//          val password = "secret"
//          val salt = None
//
//          mockIdentityId.userId returns userId
//          mockIdentityId.providerId returns providerId
//
//          mockAuthMethod.method returns method
//
//          mockPasswordInfo.hasher returns hasher
//          mockPasswordInfo.password returns password
//          mockPasswordInfo.salt returns salt
//
//          mockIdentity.identityId returns mockIdentityId
//          mockIdentity.firstName returns firstName
//          mockIdentity.lastName returns lastName
//          mockIdentity.fullName returns fullName
//          mockIdentity.email returns email
//          mockIdentity.avatarUrl returns avatarUrl
//          mockIdentity.authMethod returns mockAuthMethod
//          mockIdentity.oAuth1Info returns Option(mockOAuth1Info)
//          mockIdentity.oAuth2Info returns Option(mockOAuth2Info)
//          mockIdentity.passwordInfo returns Option(mockPasswordInfo)
//
//          val userDAO = new UserDAO
//          val identityDAO = new IdentityDAO
//          val u = userDAO.create(mockIdentity, false)
//          val i = identityDAO.create(u, mockIdentity, true)
//
//          i.user must be equalTo u
//          i.identityId.userId must be equalTo mockIdentityId.userId
//          i.identityId.providerId must be equalTo mockIdentityId.providerId
//          i.firstName must be equalTo firstName
//          i.lastName must be equalTo lastName
//          i.fullName must be equalTo fullName
//          i.email must be equalTo email
//          i.avatarUrl must be equalTo avatarUrl
//
//          i.authMethod.method must be equalTo method
//          i.oAuth1Info must be equalTo None
//          i.oAuth2Info must be equalTo None
//
//          i.passwordInfo.get.hasher must be equalTo hasher
//          i.passwordInfo.get.password must be equalTo password
//          i.passwordInfo.get.salt must be equalTo None
//
//          val persisted = identityDAO.findOneByEmailAndProvider("susan@gmail.com", "google")
//          persisted.identityId.userId must be equalTo userId
//          persisted.identityId.providerId must be equalTo providerId
//        }})
//      }
//    }
//  }

}
