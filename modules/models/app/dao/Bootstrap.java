package dao;

import play.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import play.db.jpa.JPA;
import play.libs.F;
import play.libs.Yaml;

import javax.persistence.EntityManager;


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
                    try {
                        final InputStream is = new FileInputStream(new File(yaml));
                        final List list = (List) Yaml.load(is, Bootstrap.class.getClassLoader());
                        Logger.info("===================================================");
                        for (Object o : list) {
                            Logger.info(o.toString());
                            em.persist(o);
                            count++;
                        }
                        Logger.info("===================================================");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                return count;
            }
        });
    }

}