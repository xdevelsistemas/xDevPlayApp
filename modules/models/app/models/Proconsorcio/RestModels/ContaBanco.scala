package models.Proconsorcio.RestModels

import models.Proconsorcio.ContaBanco
import play.api.libs.json
import play.api.libs.json._
import _root_.util.{xDevForm, TpResponse, Tpval, xDevSerialize}

/**
 * Created by claytonsantosdasilva on 02/08/14.
 *
 * Todos os modelos de tratamento de conta bancária
 *
 */


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




class ContaBancoForm extends xDevSerialize with xDevForm[ContaBanco,ContaBancoForm] {
  var status : TpResponse = new TpResponse("1","")
  var codigo : Tpval = new Tpval("","")
  var padrao : Tpval = new Tpval("","")
  var numBanco: Tpval = new Tpval("","")
  var nomeBanco: Tpval = new Tpval("","")
  var agencia: Tpval = new Tpval("","")
  var conta: Tpval = new Tpval("","")

  def serialize(): JsObject = {

    Json.obj(
      "resp"->status.serialize(),
      "fields"->Json.obj("codigo"-> this.codigo.serialize(),
                        "padrao"-> this.padrao.serialize(),
                        "numBanco"-> this.numBanco.serialize(),
                        "nomeBanco"-> this.nomeBanco.serialize(),
                        "agencia"-> this.agencia.serialize(),
                        "conta"-> this.conta.serialize())
    )
  }

  def read(yobj : JsValue) : ContaBanco = {
    val ContaBanco = new ContaBanco()

    this.padrao.value = (yobj \ "fields" \ "padrao" \ "value").as[String]
    this.numBanco.value = (yobj \ "fields" \ "numBanco" \ "value").as[String]
    this.nomeBanco.value = (yobj \ "fields" \ "nomeBanco" \ "value").as[String]
    this.agencia.value = (yobj \ "fields" \ "agencia" \ "value").as[String]
    this.conta.value = (yobj \ "fields" \ "conta" \ "value").as[String]

    ContaBanco.nomeBanco =this.nomeBanco.value;
    ContaBanco.numBanco = this.numBanco.value;
    ContaBanco.agencia = this.agencia.value;
    ContaBanco.conta = this.conta.value;
    ContaBanco.padrao = if(this.padrao.value.equals("1")){true}else{false}




    return ContaBanco
  }


  def read(cta : ContaBanco, resp : TpResponse) : ContaBancoForm = {
    this.status = resp
    this.padrao.value = if(cta.padrao){"1"}else{"0"}
    this.numBanco.value = cta.numBanco
    this.nomeBanco.value = cta.nomeBanco
    this.agencia.value = cta.agencia
    this.conta.value = cta.conta
    this.codigo.value = cta.uuid

    return this
  }




}

class LstContaBanco(ylista :List[ContaBanco]) extends xDevSerialize {

  def serialize(): JsObject = {
    Json.obj(
      "lista"->
        this.ylista.map { t=>
          Json.obj(
            "codigo"-> t.uuid.toString,
            "padrao"-> {if(t.padrao){"1"}else{"0"}},
            "banco"-> new Banco(t.numBanco,t.nomeBanco).serialize(),
            "agencia"-> t.agencia,
            "conta"-> t.conta
          ) }
    )
  }
}





