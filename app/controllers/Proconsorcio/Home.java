package controllers.Proconsorcio;

import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;
import scala.Some;
import securesocial.core.java.SecureSocial;
import views.html.Proconsorcio.*;
import securesocial.core.*;


/**
 * Created by claytonsantosdasilva on 11/06/14.
 */
 public class Home extends Controller{

    @SecureSocial.UserAwareAction
    public static Result home()
    {
        return ok(index.render(_user()));
    }


    @SecureSocial.SecuredAction
    public static Result alterarcodigo()
    {
        return ok (alterarcodigo.render(_user()));
    }


    @SecureSocial.SecuredAction
    public static Result novacarta()
    {
        return ok(novacarta.render());
    }


    public static Result detalhes(String id) { return ok (detalhes.render(id));}



    private static scala.Option<Identity> _user() {
        return Option.apply(SecureSocial.currentUser());
    }





}


