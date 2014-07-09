package controllers.Proconsorcio

import java.lang.ProcessBuilder.Redirect

import dao.UserDAO
import play.api.Logger
import play.api.i18n.Messages
import play.api.mvc.{Request, Action}
import play.api.mvc.SimpleResult
import play.mvc.Controller
import models.Cadastro._

/**
 * Created by claytonsantosdasilva on 09/07/14.
 */
object Handles extends  Controller {

  def handleDadosCadastrais = Action { implicit request =>


    RegistrationObjects.formAlterarDados.bindFromRequest.fold (
        errors => {
          play.api.mvc.Results.BadRequest(views.html.Proconsorcio.main.render(views.html.Proconsorcio.dadoscadastrais(errors,""), "Dados Cadastrais", None))

        },

        success => {
          (new UserDAO()).alterarCadastro(((RegistrationObjects.formAlterarDados)bindFromRequest()).value);

           play.api.mvc.Results.Redirect("/dadoscadastrais")
          //play.api.mvc.Results.Ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.dadoscadastrais((RegistrationObjects.formAlterarDados)bindFromRequest(),""), "Dados Cadastrais", None))
        }

      )


  }

}
