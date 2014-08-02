package controllers.Proconsorcio

import play.api.libs.json.Json
import play.api.mvc.Action
import util.{TpElDropDown, TpDropDown}

import scala.collection.JavaConverters._
import br.com.republicavirtual.CepService
import controllers.xDevRestController
import dao.AdministradoraDAO
import models.Proconsorcio.Administradora
import play.mvc.Result
import securesocial.core.java.SecureSocial


/**
 * Created by claytonsantosdasilva on 01/08/14.
 * Controller responável por toda comunicação via rest com o front-end
 *
 */
object RestController extends xDevRestController {

  def administradoras() =  Action {

    val resp: TpDropDown = new TpDropDown() {
      codigo = "uuid"
      descricao = "Administradora"

    }


    (new AdministradoraDAO).all().asScala.filter(d =>
      d.ativo.equals(true)).sortBy(d=>d.name).foreach(d => {
      resp.List.asJava.add(new TpElDropDown() {
        codigo = d.uuid.toString(); descricao = d.name
      })
    })

//    Json.obj(
//      "uf" -> result.getUf,
//      "cidade" -> result.getCidade,
//      "bairro" -> result.getBairro,
//      "tipo_logradouro" -> result.getTipo_logradouro,
//      "logradouro" -> result.getLogradouro,
//      "resultado" -> result.getResultado,
//      "resultado_txt" -> result.getResultado_txt
//    )

    Ok(Json.toJson(resp.serialize()))





  }

  def endereco(cep: String): Result = {
    JsonResult(CepService.buscaCEP(cep))
  }


}
