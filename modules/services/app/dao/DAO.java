package dao;

import com.avaje.ebean.Ebean;
import play.Logger;
import play.libs.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class DAO {

    public static final UserDAO userDAO         = new UserDAO();
    public static final IdentityDAO identityDAO = new IdentityDAO();


    public static int startUp() {
        return startUp("conf/initial-data.yml");
    }

    public static int fakeStartUp() {
        return startUp("test/data-test.yml");
    }

    private static int startUp(String yaml) {
        try {
            final InputStream is = new FileInputStream(new File(yaml));
            final List list = (List) Yaml.load(is, DAO.class.getClassLoader());
            Logger.info("=========================================================");
            for (Object o : list) {
                Logger.info(o.toString());
            }
            Logger.info("=========================================================");
            return (new File(yaml).exists()) ? Ebean.save(list) : -1;
            // return (new File(yaml).exists()) ? Ebean.save((List) Yaml.load(yaml)) : -1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
