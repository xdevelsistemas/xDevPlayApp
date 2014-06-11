package controllers.Proconsorcio;

import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.java.SecureSocial;


/**
 * Created by claytonsantosdasilva on 08/06/14.
 */
public class LandingPage extends Controller {

	@SecureSocial.UserAwareAction
    public static Result index()
    {
		dao.UserIdentity user = (dao.UserIdentity) ctx().args.get(SecureSocial.USER_KEY);
        
        
    	if (user != null){
    		 
    		return redirect("/home");
    			
    		 
    	}else
    	{
    		 return ok(views.html.LandingPage.main.render());
    		 
    		 
    	}
    
    }


}