package controllers.Proconsorcio;

import play.mvc.Result;
import play.mvc.Controller;

/**
 * Created by claytonsantosdasilva on 11/06/14.
 */
 public class Home extends Controller{
    public static Result home()
    {
        return ok(views.html.Proconsorcio.index.render());
        
    }
}


