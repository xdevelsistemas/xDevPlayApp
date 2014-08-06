package models.Proconsorcio.RestModels

import java.text.NumberFormat
import java.util.Locale

import models.Proconsorcio.{Carta, EstatusCarta}
import play.api.libs.json.{JsValue, Json, JsObject}
import util.xDevSerialize

/**
 * Created by claytonsantosdasilva on 06/08/14.
 *
 *  Classes ref a manipulação de pesquisa de cartas de consórcio
 *
 *
 */
class Pesquisa(yobj : JsValue) {

  var administradora : String = new String
  var contemplacao : Option[EstatusCarta] = None
  var prazo_restante : String = new String
  var tipo : String = new String
  var valor_credito_max : Option[Number] = None
  var valor_credito_min : Option[Number] = None
  var valor_parcelas_max : Option[Number] = None
  var valor_parcelas_min : Option[Number] = None
  var itens_pagina : Int  = 5
  var pagina : Int  = 1
  var ordem : String = "asc"
  var ordenador : String = "preco"

  def Pesquisa(yobj : JsValue){
    val _administradora = (yobj \ "administradora").as[String]
    val _contemplacao = (yobj \"codigo_tipocarta").as[String]
//    this.codigo_administradora.value = (yobj \"codigo_tipocarta").as[String]
//    this.prazoRestante.value = (yobj \"codigo_tipocarta").as[String]
//    this.valorCredito.value = (yobj \"codigo_tipocarta").as[String]
//    this.valorEntrada.value = (yobj \"codigo_tipocarta").as[String]
//    this.valorPrestacao.value = (yobj \"codigo_tipocarta").as[String]
//    this.valorCota.value = (yobj \"codigo_tipocarta").as[String]
//    this.numCodigo.value = (yobj \"codigo_tipocarta").as[String]
//    this.codigoConta.value = (yobj \"codigo_tipocarta").as[String]
  }




  def query = processa




  def processa  : JsObject = {
      Json.obj()
  }


}


/** *
  *
  * xdevel sistemas - json de requisição de pesquisa
  *       {
               "administradora": "",
               "contemplacao": "",
               "prazo_restante": "",
               "tipo": "",
               "valor_credito_max": "",
               "valor_credito_min": "",
               "valor_parcelas_max": "",
               "valor_parcelas_min": "",
               "pagina": "",
               "ordenador": "",
               "ordem": ""
           }

          ordenador = 'preco' || 'administradora'
          ordem = 'asc' || 'desc'

  *
  *
  */





class ResultadoPesquisa (ytotal : Int, ylista : List[Carta], ypaginas : Int) extends xDevSerialize{
  val ptBr = new Locale("pt", "BR")
  val numberFormat = NumberFormat.getInstance(ptBr)

  val total : Int  = ytotal
  val lista : List[Carta]   = ylista
  val paginas : Int = ypaginas

  override def serialize(): JsObject = {
    Json.obj(
      "total" -> this.total,
      "lista" -> this.lista.map( t =>
        Json.obj(
          "codigo" -> t.friendlyID.toString,
          "valorDoBem" -> NumberFormat.getCurrencyInstance(ptBr).format(t.valorCredito),
          "administradora" -> Json.obj(
            "codigo" -> t.administradora.uuid,
            "nome" -> t.administradora.name
          )
        )
      )
    )
  }
}

//{
//  "total": "45",
//  "lista": [{
//              "codigo": "1",
//              "valorDoBem": "80000",
//              "administradora": {
//                                  "codigo": "1",
//                                  "nome": "Rodobens"
//                                }
//
//             }],
//  "paginas": {
//      "total": "9"
//  }
//}