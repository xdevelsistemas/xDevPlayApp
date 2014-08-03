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
 * elementos base de todas as comunicacoes rest
 *
 */


class TpElDropDown(ycodigo: String, ydescricao :String) extends xDevSerialize {
  var codigo: String = ycodigo
  var descricao: String = ydescricao


   def serialize() =
    Json.obj(
      "codigo" -> this.codigo,
      "descricao" -> this.descricao


  )
}



trait xDevSerialize{
  def serialize() : JsObject
}

class TpResponse(yresult: String, ymessage: String) extends xDevSerialize{
  var result : String = yresult
  var message: String = ymessage


   def serialize() =
        Json.obj(
        "result" -> this.result,
        "message"-> this.message
        )

  
}


class Tpval(yvalue : String,yerror : String) extends xDevSerialize{

  var value : String = yvalue
  var error: String = yerror


  def serialize() =
    Json.obj(
      "result" -> this.value,
      "message"-> this.error
    )
}

class TpDropDown(ycodigo: String, ydescricao: String, ylista: ArrayList[TpElDropDown]) extends xDevSerialize {
  var codigo: String = ycodigo
  var descricao: String = ydescricao
  var lista: ArrayList[TpElDropDown] = ylista


  def serialize() =
    Json.obj(
      "codigo" -> this.codigo,
      "descricao" -> this.descricao,
      "lista" -> this.lista.asScala.map { t=>
        t.serialize()
      }


  )
  
  
}



