import play.api.test.{FakeRequest, WithApplication, FakeApplication, PlaySpecification}

import dao.DAO.userDAO


class ApplicationScenario extends PlaySpecification {

  "TEST initialization data" should {
    "load properly" in {
      new WithApplication(FakeApplication()) {
        userDAO.findOne("email", "admin@example.com").get.email must be equalTo "admin@example.com"
      }
    }
  }
}
