package controllers;

import play.mvc.Controller;
import play.mvc.Result;



/**
 * Created by claytonsantosdasilva on 08/06/14.
 */
public class LandingPage extends Controller {

    public static Result index()
    {
        return ok(views.html.LandingPage.index.render());
    }

}
