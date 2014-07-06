package controllers.Proconsorcio;

import play.mvc.Controller;
import play.mvc.Result;
import scala.Option;
import securesocial.core.java.SecureSocial;
import securesocial.core.*;
import play.libs.Json;
import br.com.republicavirtual.*;

/**
 * Created by claytonsantosdasilva on 11/06/14.
 */
 public class Home extends Controller{

    @SecureSocial.UserAwareAction
    public static Result home()
    {
        return ok(views.html.Proconsorcio.main.render(views.html.Proconsorcio.index.render(), "Pesquisa", _user()));
    }


    @SecureSocial.SecuredAction
    public static Result alterarcodigo()
    {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.alterarcodigo.render(), "Alterar Código", _user()));
    }


    @SecureSocial.SecuredAction
    public static Result novacarta()
    {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.novacarta.render(), "Nova Carta", _user()));
    }


    public static Result detalhes(String id) { return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.detalhes.render(id, _user()), "Detalhes", _user()));}


    public static Result pesquisa(String query) {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.pesquisa.render(query), "Pesquisa", _user()));
    }

    public static Result pesquisa_clean() {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.pesquisa.render(""), "Pesquisa", _user()));
    }


    public static Result simulador() {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.simulador.render(), "Simulador", _user()));
    }

    @SecureSocial.SecuredAction
    public static Result dadoscadastrais() {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.dadoscadastrais.render(), "Dados Cadastrais", _user()));
    }

    @SecureSocial.SecuredAction
    public static Result escritorio() {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.escritorio.render(), "Escritório Online", _user()));
    }


    public static Result faleconosco() {
        return ok (views.html.Proconsorcio.main.render(views.html.Proconsorcio.faleconosco.render(), "Fale Conosco", _user()));
    }

    private static scala.Option<Identity> _user() {
        return Option.apply(SecureSocial.currentUser());
    }




    public static Result getEndereco(String cep){
        return ok(Json.toJson(((CepServiceVO)CepService.buscaCEP(cep))));
    }

    public static Result getUF(){
        return redirect("/assets/App/Mockup/Estados.json");
    }

    public static Result getBanco(){
        return redirect("/assets/App/Mockup/Bancos.json");
    }








}


