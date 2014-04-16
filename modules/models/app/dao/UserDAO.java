package dao;

public class UserDAO extends AbstractDAO<models.User, models.User_> {

    public UserDAO() {
        super(models.User.class, models.User_.class);
    }

    public models.User create(securesocial.core.Identity i, boolean save) {
        models.User o = new models.User();
        o.email = i.email().get();
        if (i.fullName() != null) {
            o.realName = i.fullName();
        } else if (i.firstName() != null) {
            o.realName = i.firstName();
        } else {
            o.realName = i.identityId().userId();
        }

        if (save) save(o);

        return o;
    }

}
