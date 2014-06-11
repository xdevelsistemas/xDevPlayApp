package dao;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;


public class Bootstrap {

    public static int startUp() throws Throwable {
        return startUp("conf/initial-data.yml");
    }

    public static int fakeStartUp()throws Throwable {
        return startUp("test/data-test.yml");
    }

    private static int startUp(String yaml) throws Throwable {
        return JPA.withTransaction("default", false, new play.libs.F.Function0<Integer>() {
            public Integer apply() throws Throwable {
                int count = 0;
                UserDAO userDAO = new UserDAO();
                if (userDAO.findOne("email", "admin@example.com") == null) {
                    final EntityManager em = JPA.em();

                }
                return count;
            }
        });
    }

}