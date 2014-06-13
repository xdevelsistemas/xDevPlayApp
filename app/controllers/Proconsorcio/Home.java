package controllers.Proconsorcio;

import play.mvc.Result;
import play.mvc.Controller;
import securesocial.core.java.SecureSocial;

/**
 * Created by claytonsantosdasilva on 11/06/14.
 */
 public class Home extends Controller{

    @SecureSocial.UserAwareAction
    public static Result home()
    {

        dao.UserIdentity user = (dao.UserIdentity) ctx().args.get(SecureSocial.USER_KEY);

        if  (user != null)
        {
            return ok(views.html.Proconsorcio.logado.render());
        }else
        {
            return ok(views.html.Proconsorcio.index.render());
        }

    }


    @SecureSocial.SecuredAction
    public static Result alterarcodigo()
    {
        return ok (views.html.Proconsorcio.alterarcodigo.render());
    }
}


