package controllers.Proconsorcio;

import play.mvc.Result;
import play.mvc.Controller;
import securesocial.core.java.SecureSocial;
import views.html.Proconsorcio.*;

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
        return ok (alterarcodigo.render());
    }


    @SecureSocial.SecuredAction
    public static Result novacarta()
    {
        return ok(novacarta.render());
    }


    public static Result detalhes(String id) { return ok (detalhes.render(id));}


}


