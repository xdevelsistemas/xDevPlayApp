package controllers.Proconsorcio;

import br.com.republicavirtual.CepService;
import br.com.republicavirtual.CepServiceVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.xDevController;
import play.api.mvc.Result;

import play.*;
import play.libs.Json;
import play.mvc.*;
import static play.libs.Json.newObject;
import static play.libs.Json.toJson;

import java.io.IOException;


/**
 * Created by claytonsantosdasilva on 01/08/14.
 */
public class JsonControlller extends xDevController {

//    public static Result getEndereco(String cep) {
//        ObjectMapper mapper = new ObjectMapper();
//
//
//
//        CepServiceVO end = CepService.buscaCEP(cep);
//        JsonNode result = Json.toJson(end);
//
//        try {
//
//            return Results.ok(newObject());
//        } catch (JsonProcessingException e) {
//            return ok(e.getLocalizedMessage());
//        } catch (IOException e1) {
//            return ok(e1.getLocalizedMessage());
//        }
//
//
//    }
}
