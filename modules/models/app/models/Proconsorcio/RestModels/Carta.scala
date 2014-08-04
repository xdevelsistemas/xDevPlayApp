package models.Proconsorcio.RestModels

import java.util.UUID

import dao.{UserDAO, AdministradoraDAO, TipoCartaDAO}
import models.Proconsorcio.{EstatusCarta, Carta, ContaBanco}
import models.User
import play.api.libs.json.{JsValue, Json, JsObject}
import util.{xDevForm, xDevSerialize, Tpval, TpResponse}

/**
 * Created by claytonsantosdasilva on 04/08/14.
 */
class CartaForm extends xDevSerialize with xDevForm[Carta,CartaForm]{


  var status : TpResponse = new TpResponse("1","")
  var codigo  : Tpval = new Tpval("","")
  var id  : Tpval = new Tpval("","")
  var codigo_statuscarta  : Tpval = new Tpval("","")
  var codigo_tipocarta  : Tpval = new Tpval("","")
  var codigo_administradora  : Tpval = new Tpval("","")
  var prazoRestante  : Tpval = new Tpval("","")
  var valorCredito  : Tpval = new Tpval("","")
  var valorEntrada  : Tpval = new Tpval("","")
  var valorPrestacao  : Tpval = new Tpval("","")
  var valorCota  : Tpval = new Tpval("","")
  var numCodigo  : Tpval = new Tpval("","")
  var numBancoDeposito  : Tpval = new Tpval("","")
  var nomeBancoDeposito  : Tpval = new Tpval("","")
  var agenciaDeposito  : Tpval = new Tpval("","")
  var contaDeposito  : Tpval = new Tpval("","")


  def validate (yobj : CartaForm, user: User) : CartaForm = {
      val dao = new UserDAO

      if(!dao.verificanumCodigoDigitado(user.email,user.providerId,yobj.numCodigo.value))
      {
        yobj.status.result = "0"
        yobj.status.message = "Código de Acesso inválido"
        yobj.numCodigo.value = ""
        yobj.numCodigo.error = yobj.status.message
      }

      return yobj
  }







  def serialize(): JsObject = {

    Json.obj(
      "resp"->status.serialize(),
      "fields"->Json.obj(
        "codigo"-> this.codigo.serialize(),
        "id"-> this.id.serialize(),
        "codigo_statuscarta"-> this.codigo_statuscarta.serialize(),
        "codigo_tipocarta"-> this.codigo_tipocarta.serialize(),
        "codigo_administradora"-> this.codigo_administradora.serialize(),
        "prazoRestante"-> this.prazoRestante.serialize(),
        "valorCredito"-> this.valorCredito.serialize(),
        "valorEntrada"-> this.valorEntrada.serialize(),
        "valorPrestacao"-> this.valorPrestacao.serialize(),
        "valorCota"-> this.valorCota.serialize(),
        "numCodigo"-> this.numCodigo.serialize(),
        "numBancoDeposito"-> this.numBancoDeposito.serialize(),
        "nomeBancoDeposito"-> this.nomeBancoDeposito.serialize(),
        "agenciaDeposito"-> this.agenciaDeposito.serialize(),
        "contaDeposito"-> this.contaDeposito.serialize()
      )
    )
  }


  private def _read(yobj : JsValue) : CartaForm = {
    this.codigo_statuscarta.value = (yobj \ "fields" \ "codigo_statuscarta" \ "value").as[String]
    this.codigo_tipocarta.value = (yobj \ "fields" \ "codigo_tipocarta" \ "value").as[String]
    this.codigo_administradora.value = (yobj \ "fields" \ "codigo_administradora" \ "value").as[String]
    this.prazoRestante.value = (yobj \ "fields" \ "prazoRestante" \ "value").as[String]
    this.valorCredito.value = (yobj \ "fields" \ "valorCredito" \ "value").as[String]
    this.valorEntrada.value = (yobj \ "fields" \ "valorEntrada" \ "value").as[String]
    this.valorPrestacao.value = (yobj \ "fields" \ "valorPrestacao" \ "value").as[String]
    this.valorCota.value = (yobj \ "fields" \ "valorCota" \ "value").as[String]
    this.numCodigo.value = (yobj \ "fields" \ "numCodigo" \ "value").as[String]
    this.numBancoDeposito.value = (yobj \ "fields" \ "numBancoDeposito" \ "value").as[String]
    this.nomeBancoDeposito.value = (yobj \ "fields" \ "nomeBancoDeposito" \ "value").as[String]
    this.agenciaDeposito.value = (yobj \ "fields" \ "agenciaDeposito" \ "value").as[String]
    this.contaDeposito.value = (yobj \ "fields" \ "contaDeposito" \ "value").as[String]

    return this
  }

  def read(yobj : JsValue) : Carta = {
    return  _instantiate(_read(yobj))
  }

  private def _instantiate(yobj : CartaForm) : Carta = {
    val xCarta = new Carta()
    xCarta.statusCarta = EstatusCarta.valueOf(yobj.codigo_statuscarta.value)
    xCarta.tipoCarta = (new TipoCartaDAO).findOne(UUID.fromString(yobj.codigo_tipocarta.value))
    xCarta.administradora = (new AdministradoraDAO).findOne(UUID.fromString(yobj.codigo_administradora.value))
    xCarta.prazoRestante = Integer.parseInt(yobj.prazoRestante.value)
    xCarta.valorCredito = java.lang.Float.parseFloat(yobj.valorCredito.value)
    xCarta.valorEntrada = java.lang.Float.parseFloat(yobj.valorEntrada.value)
    xCarta.valorPrestacao = java.lang.Float.parseFloat(yobj.valorPrestacao.value)
    xCarta.valorCota = java.lang.Float.parseFloat(yobj.valorCota.value)
    xCarta.numBancoDeposito = yobj.numBancoDeposito.value
    xCarta.nomeBancoDeposito = yobj.nomeBancoDeposito.value
    xCarta.agenciaDeposito = yobj.agenciaDeposito.value
    xCarta.contaDeposito = yobj.contaDeposito.value

    return xCarta
  }

  def readAndValidate (yobj : JsValue, user: User) : Carta = {
    return _instantiate(validate(_read(yobj),user))
  }



  def read(cta : Carta, resp : TpResponse) : CartaForm = {
    this.status = resp
    this.id.value = cta.friendlyID.id.toString
    this.codigo.value = cta.uuid
    this.codigo_statuscarta.value  = cta.statusCarta.ordinal().toString
    this.codigo_administradora.value = cta.administradora.uuid
    this.codigo_tipocarta.value = cta.tipoCarta.uuid
    this.prazoRestante.value = cta.prazoRestante.toString
    this.valorCredito.value = cta.valorCredito.toString
    this.valorEntrada.value = cta.valorEntrada.toString
    this.valorPrestacao.value = cta.valorPrestacao.toString
    this.valorCota.value = cta.valorCota.toString
    this.numBancoDeposito.value = cta.numBancoDeposito
    this.nomeBancoDeposito.value = cta.nomeBancoDeposito
    this.agenciaDeposito.value = cta.agenciaDeposito
    this.contaDeposito.value = cta.contaDeposito


    return this
  }


}
