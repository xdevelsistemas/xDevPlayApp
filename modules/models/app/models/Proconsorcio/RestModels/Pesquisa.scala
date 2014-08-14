package models.Proconsorcio.RestModels

import java.text.NumberFormat
import java.util.{UUID, Locale}
import java.lang.Integer
import dao.{TipoCartaDAO, AdministradoraDAO, CartaDAO, CartaDAOextend}
import models.Proconsorcio
import models.Proconsorcio.{TipoCarta, Administradora, Carta, EstatusCarta}
import play.api.libs.json.{JsValue, Json, JsObject}
import util.xDevSerialize
import scala.collection.JavaConverters._
/**
 * Created by claytonsantosdasilva on 06/08/14.
 *
 * Classes ref a manipulação de pesquisa de cartas de consórcio
 *
 *
 */
class Pesquisa(yobj: JsValue) {
  val ptBr = new Locale("pt", "BR")
  val numberFormat = NumberFormat.getInstance(ptBr)

  var administradora: Option[Administradora] = None
  var contemplacao: String = new String
  var prazo_restante: String = new String
  var tipo: Option[TipoCarta] = None
  var valor_credito_max: Option[Number] = None
  var valor_credito_min: Option[Number] = None
  var valor_parcelas_max: Option[Number] = None
  var valor_parcelas_min: Option[Number] = None
  var itens_pagina: Int = 5
  var pagina: Int = 1
  var ordem: String = "asc"
  var ordenador: String = "valor_credito"

  try {

    val _administradora = (yobj \ "administradora").as[String]
    val _contemplacao = (yobj \ "contemplacao").as[String]
    val _prazo_restante = (yobj \ "prazo_restante").as[String]
    val _tipo = (yobj \ "tipo").as[String]
    val _valor_credito_min = (yobj \ "valor_credito_min").as[String]
    val _valor_credito_max = (yobj \ "valor_credito_max").as[String]
    val _valor_parcelas_min = (yobj \ "valor_parcelas_min").as[String]
    val _valor_parcelas_max = (yobj \ "valor_parcelas_max").as[String]
    val _itens_pagina = (yobj \ "itens_pagina").as[String]
    val _pagina = (yobj \ "pagina").as[String]
    val _ordem = (yobj \ "ordem").as[String]
    val _ordenador = (yobj \ "ordenador").as[String]

    this.administradora = if(!_administradora.isEmpty && _administradora != "-1" ) Some((new AdministradoraDAO).findByName(_administradora)) else None
    this.contemplacao = if(!_contemplacao.isEmpty && _contemplacao != "-1") { if (_contemplacao.equals("Contemplada")) {EstatusCarta.contemplada.toString}else{EstatusCarta.naocontemplada.toString}} else ""
    this.prazo_restante  = if(_prazo_restante != "-1")  _prazo_restante else ""
    this.tipo = if(!_tipo.isEmpty && _tipo != "-1" ) Some((new TipoCartaDAO).findByName(_tipo)) else None
    this.valor_credito_max = if (_valor_credito_max.isEmpty) {None} else {Some(NumberFormat.getCurrencyInstance(ptBr).parse(_valor_credito_max))}
    this.valor_credito_min = if (_valor_credito_min.isEmpty) {None} else {Some(NumberFormat.getCurrencyInstance(ptBr).parse(_valor_credito_min))}
    this.valor_parcelas_max = if (_valor_parcelas_max.isEmpty) {None} else {Some(NumberFormat.getCurrencyInstance(ptBr).parse(_valor_parcelas_max))}
    this.valor_parcelas_min = if (_valor_parcelas_min.isEmpty) {None} else {Some(NumberFormat.getCurrencyInstance(ptBr).parse(_valor_parcelas_min))}
    if (!_itens_pagina.isEmpty) this.itens_pagina  = Integer.parseInt(_itens_pagina)
    if (!_pagina.isEmpty) this.pagina  = Integer.parseInt(_pagina)
    if (!_ordem.isEmpty) this.ordem = _ordem
    if (!_ordenador.isEmpty) this.ordenador = _ordenador

  } catch {
    case e: Exception =>
  }


  def listar : ResultadoPesquisa = {
    (new CartaDAO).PesquisaCarta(this)
  }





}


/** *
  *
  * xdevel sistemas - json de requisição de pesquisa
  * {
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


class ResultadoPesquisa(ytotal: Long, ylista: java.util.List[Carta]) extends xDevSerialize {
  val ptBr = new Locale("pt", "BR")
  val numberFormat = NumberFormat.getInstance(ptBr)

  val total: Long = ytotal
  val lista: List[Carta] = ylista.asScala.toList

  override def serialize(): JsObject = {
    Json.obj(
      "total" -> this.total,
      "lista" -> this.lista.map(t =>
        Json.obj(
          "codigo" -> t.friendlyID.id.toString,
          "valorDoBem" -> NumberFormat.getCurrencyInstance(ptBr).format(t.valorCredito),
          "administradora" -> Json.obj(
            "codigo" -> t.administradora.uuid,
            "nome" -> t.administradora.name
          ),
          "tipo" -> Json.obj(
            "codigo" -> t.tipoCarta.uuid,
            "nome" -> t.tipoCarta.name
          ),
          "valorDaEntrada" -> NumberFormat.getCurrencyInstance(ptBr).format(t.valorEntrada),
          "prazoRestante" -> t.prazoRestante.toString
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
//            "tipo": {
//                      "codigo": "1",
//                      "nome": "Carro"
//                    },
//            "valorDaEntrada": "17000",
//            "prazoRestante": "18",
//
//             }],
//  "paginas": {
//      "total": "9"
//  }
//}