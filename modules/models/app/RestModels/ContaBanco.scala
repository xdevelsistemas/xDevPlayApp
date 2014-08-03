package RestModels

import models.Proconsorcio.ContaBanco
import play.api.libs.json.JsObject
import play.api.libs.json.{JsObject, Writes, JsNull, Json}
import util.{TpResponse, Tpval, xDevSerialize}

/**
 * Created by claytonsantosdasilva on 02/08/14.
 *
 * Todos os modelos de
 *
 */

class Banco(ycodigo:String,ynome:String) extends xDevSerialize{
  var codigo : String = this.ycodigo
  var nome : String = this.ynome

  def serialize(): JsObject = {
    Json.obj(
      "codigo" -> this.codigo,
      "nome" -> this.nome
    )
  }

}

class ContaBancoForm extends xDevSerialize{
  var status : TpResponse = new TpResponse("0","")
  var codigo : Tpval = new Tpval("","")
  var padrao : Tpval = new Tpval("","")
  var numBanco: Tpval = new Tpval("","")
  var nomeBanco: Tpval = new Tpval("","")
  var agencia: Tpval = new Tpval("","")
  var conta: Tpval = new Tpval("","")

  def serialize(): JsObject = {

          Json.obj(
            "codigo"-> this.codigo.serialize(),
            "padrao"-> this.padrao.serialize(),
            "banco"-> Json.obj("codigo" -> this.numBanco.value,"nome" -> this.agencia.value),
            "agencia"-> this.agencia.serialize(),
            "conta"-> this.conta.serialize()
          )
  }


}


class ListaContaBanco(ylista :List[ContaBanco]) extends xDevSerialize {

  def serialize(): JsObject = {
    Json.obj(
      "lista"->
        this.ylista.map { t=>
          Json.obj(
            "codigo"-> t.uuid.toString,
            "padrao"-> t.padrao.toString,
            "banco"-> new Banco(t.numBanco,t.nomeBanco).serialize(),
            "agencia"-> t.agencia,
            "conta"-> t.conta
          ) }
    )
  }
}
/*
    {"lista": [
        {

            "padrao": "1",
            "codigo": "42",
            "banco": {
                "codigo": "2",
                "nome": "Banco do Brasil"
            },
            "agencia": "0000",
            "conta": "123278954"
        },
        {
            "padrao": "0",
            "codigo": "88",
            "banco": {
                "codigo": "5",
                "nome": "Santander"
            },
            "agencia": "654",
            "conta": "55668844"
        },
        {
            "padrao": "0",
            "codigo": "11",
            "banco": {
                "codigo": "8",
                "nome": "Itaú"
            },
            "agencia": "897",
            "conta": "6654858458"
        }
    ]}
*/


