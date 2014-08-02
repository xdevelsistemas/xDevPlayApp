package util

import java.util
import java.util.ArrayList
import scala.collection.JavaConverters._
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.node.ObjectNode
import dao.AdministradoraDAO
import play.api.libs.json.{JsObject, Writes, JsNull, Json}

import scala.util.parsing.json.JSONArray

/**
 * Created by claytonsantosdasilva on 01/08/14.
 */

/**
 * Created by claytonsantosdasilva on 04/07/14.
 */

class TpElDropDown extends xDevSerialize {
  var codigo: String = ""
  var descricao: String = ""


  def serialize() =
    Json.obj(
      "codigo" -> this.codigo,
      "descricao" -> this.descricao


  )


}

trait xDevSerialize{
  def serialize() : JsObject
}

class TpResponse extends xDevSerialize{
  var result : String = ""
  var message: String = ""

  def apply(yresult: String, ymessage: String){
    this.result = yresult
    this.message = ymessage
  }




  def serialize() =
        Json.obj(
        "result" -> this.result,
        "message"-> this.message
        )

  
}





class TpDropDown extends xDevSerialize {
  var codigo: String = ""
  var descricao: String = ""
  var lista: ArrayList[TpElDropDown] = new ArrayList[TpElDropDown]()


  def apply(ycodigo: String, ydescricao: String, ylista: ArrayList[TpElDropDown]){
     this.codigo = ycodigo
     this.descricao = ydescricao
     this.lista = ylista
     return this
  }

  def serialize() =
    Json.obj(
      "codigo" -> this.codigo,
      "descricao" -> this.descricao,
      "Lista" -> this.lista.asScala.map { t=>
        t.serialize()
      }


  )
  
  
}



