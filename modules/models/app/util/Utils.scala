package util

import java.util
import java.util.ArrayList

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import play.api.libs.json.JsNull
import play.api.libs.json.Json

/**
 * Created by claytonsantosdasilva on 01/08/14.
 */

/**
 * Created by claytonsantosdasilva on 04/07/14.
 */

@JsonSerialize
class TpElDropDown {
  var codigo: String = ""
  var descricao: String = ""

  def apply(ycodigo: String, ydescricao: String){
    codigo = ycodigo
    descricao = ydescricao

  }
}






@JsonSerialize
class TpDropDown {
  var codigo: String = ""
  var descricao: String = ""
  var List: List[TpElDropDown] = Nil

  def serialize() = {

    Json.obj(
      "codigo" -> this.codigo,
      "descricao" -> this.codigo

    )

  }
}



