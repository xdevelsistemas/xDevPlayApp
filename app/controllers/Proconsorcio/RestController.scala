package controllers.Proconsorcio

import controllers.Proconsorcio.Application._

/**
 * Created by claytonsantosdasilva on 01/08/14.
 * Controller responável por toda comunicação via rest com o front-end
 *
 */
object RestController {

  def administradoras  = SecuredAction { implicit request =>
    Ok(views.html.App.main.render(views.html.Proconsorcio.escritorio.render, "Escritório Online", _user, request))
  }

}
