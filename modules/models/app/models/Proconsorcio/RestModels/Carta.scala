package models.Proconsorcio.RestModels

import java.text.NumberFormat
import java.util
import java.lang.Long
import java.util.{Locale, UUID}

import _root_.util.{Tpval, TpResponse, xDevForm, xDevSerialize}
import dao.{ContaBancoDAO, UserDAO, AdministradoraDAO, TipoCartaDAO}
import models.Proconsorcio._
import models.User
import play.api.libs.json.{JsValue, Json, JsObject}


/**
 * Created by claytonsantosdasilva on 04/08/14.
 *
 * Criado para gerenciar cartas de consorcio ( parte de comunicação via rest API)
 */
class CartaForm extends xDevSerialize with xDevForm[Carta,CartaForm]{

  private val ptBr = new Locale("pt", "BR")
  private val numberFormat = NumberFormat.getInstance(ptBr)


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
  val codigoConta : Tpval = new Tpval("","")
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

       yobj
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
    this.codigoConta.value = (yobj \ "fields" \ "codigo_conta" \ "value").as[String]

//    this.numBancoDeposito.value = (yobj \ "fields" \ "numBancoDeposito" \ "value").as[String]
//    this.nomeBancoDeposito.value = (yobj \ "fields" \ "nomeBancoDeposito" \ "value").as[String]
//    this.agenciaDeposito.value = (yobj \ "fields" \ "agenciaDeposito" \ "value").as[String]
//    this.contaDeposito.value = (yobj \ "fields" \ "contaDeposito" \ "value").as[String]

     this
  }

  def read(yobj : JsValue) : Carta = {
      _instantiate(_read(yobj))
  }

  private def _instantiate(yobj : CartaForm) : Carta = {
    val xCarta = new Carta()
    xCarta.statusCarta = EstatusCarta.valueOf(yobj.codigo_statuscarta.value)
    xCarta.tipoCarta = (new TipoCartaDAO).findOne(UUID.fromString(yobj.codigo_tipocarta.value))
    xCarta.administradora = (new AdministradoraDAO).findOne(UUID.fromString(yobj.codigo_administradora.value))
    xCarta.valorCredito = NumberFormat.getCurrencyInstance(ptBr).parse(yobj.valorCredito.value)
    xCarta.valorEntrada = NumberFormat.getCurrencyInstance(ptBr).parse(yobj.valorEntrada.value)
    xCarta.valorPrestacao = NumberFormat.getCurrencyInstance(ptBr).parse(yobj.valorPrestacao.value)

    try {
      xCarta.prazoRestante = Long.parseLong(yobj.prazoRestante.value)

      if (xCarta.prazoRestante > 9999 || xCarta.prazoRestante < 0 ) new Exception("Prazo Restante deve ser entre 0 e 9999")


    }catch {
      case  e: Exception => throw new Exception("Prazo Restante deve ser entre 0 e 9999")
    }

    try {
      xCarta.valorCota = Long.parseLong(yobj.valorCota.value)

      if (xCarta.valorCota > 999999 || xCarta.valorCota < 0 ) new Exception("Cota deve ser entre 0 e 999999")


    }catch {
      case  e: Exception => throw new Exception("Cota deve ser entre 0 e 999999")
    }



    val codigo_conta =  (new ContaBancoDAO).findOne(UUID.fromString(yobj.codigoConta.value))

    xCarta.numBancoDeposito = codigo_conta.numBanco
    xCarta.nomeBancoDeposito = codigo_conta.nomeBanco
    xCarta.agenciaDeposito = codigo_conta.agencia
    xCarta.contaDeposito = codigo_conta.conta

     xCarta
  }

  def readAndValidate (yobj : JsValue, user: User) : Carta = {
     _instantiate(validate(_read(yobj),user))
  }



  def read(cta : Carta, resp : TpResponse) : CartaForm = {
    this.status = resp
    this.id.value = cta.friendlyID.id.toString
    this.codigo.value = cta.uuid
    this.codigo_statuscarta.value  = cta.statusCarta.toString
    this.codigo_administradora.value = cta.administradora.uuid
    this.codigo_tipocarta.value = cta.tipoCarta.uuid
    this.prazoRestante.value = cta.prazoRestante.toString
    this.valorCredito.value = NumberFormat.getCurrencyInstance(ptBr).format(cta.valorCredito)
    this.valorEntrada.value = NumberFormat.getCurrencyInstance(ptBr).format(cta.valorEntrada)
    this.valorPrestacao.value = NumberFormat.getCurrencyInstance(ptBr).format(cta.valorPrestacao)
    this.valorCota.value = cta.valorCota.toString
    this.numBancoDeposito.value = cta.numBancoDeposito
    this.nomeBancoDeposito.value = cta.nomeBancoDeposito
    this.agenciaDeposito.value = cta.agenciaDeposito
    this.contaDeposito.value = cta.contaDeposito


     this
  }


}





class TStatusCartaAdm(yval : EstatusAdministrativo , ytp : ETipoTransacao) extends xDevSerialize{

  val status = yval
  val tipotransacao = ytp



  def getDesc : String  = {


    if (this.tipotransacao.equals(ETipoTransacao.Compra)) {

       this.status match {
        case EstatusAdministrativo.AguardandoAprovacao => "Aguardando Aprovação"
        case EstatusAdministrativo.Aprovado => "Disponível"
        case EstatusAdministrativo.EmProcessodeCompra => "Em processo de compra"
        case EstatusAdministrativo.VendaAutorizada => "Autorizado para pagamento"
        case EstatusAdministrativo.Estornado => "Estornada"
        case EstatusAdministrativo.Reprovado => "Reprovada"
        case EstatusAdministrativo.Cancelado => "Cancelada"
        case EstatusAdministrativo.Bloqueio => "Bloqueada"
        case EstatusAdministrativo.Concluida => "Comprado"
        case EstatusAdministrativo.AguardandoAvaliacao => "Aguardando Avaliação do moderador"
        case EstatusAdministrativo.Excluida => "Excluída"

      }
    } else {
      if (this.tipotransacao.equals(ETipoTransacao.Venda)) {

         this.status match {
          case EstatusAdministrativo.AguardandoAprovacao => "Aguardando Aprovação"
          case EstatusAdministrativo.Aprovado => "Anunciado"
          case EstatusAdministrativo.EmProcessodeCompra => "Aguardando autorização de venda"
          case EstatusAdministrativo.VendaAutorizada => "Aguardando Pagamento da contraparte"
          case EstatusAdministrativo.Estornado => "Estornada"
          case EstatusAdministrativo.Reprovado => "Reprovada"
          case EstatusAdministrativo.Cancelado => "Cancelada"
          case EstatusAdministrativo.Bloqueio => "Bloqueada"
          case EstatusAdministrativo.Concluida => "Vendido"
          case EstatusAdministrativo.AguardandoAvaliacao => "Aguardando Avaliação do moderador"
          case EstatusAdministrativo.Excluida => "Excluída"
        }

      } else {

         this.status match {
          case EstatusAdministrativo.AguardandoAprovacao => "Aguardando Aprovação"
          case EstatusAdministrativo.Aprovado => "Anunciado"
          case EstatusAdministrativo.EmProcessodeCompra => "Aguardando autorização de venda"
          case EstatusAdministrativo.VendaAutorizada => "Aguardando Pagamento da contraparte"
          case EstatusAdministrativo.Estornado => "Estornada"
          case EstatusAdministrativo.Reprovado => "Reprovada"
          case EstatusAdministrativo.Cancelado => "Cancelada"
          case EstatusAdministrativo.Bloqueio => "Bloqueada"
          case EstatusAdministrativo.Concluida => "Concluido"
          case EstatusAdministrativo.AguardandoAvaliacao => "Aguardando Avaliação do moderador"
          case EstatusAdministrativo.Excluida => "Excluída"

        }

      }
    }
  }


  def getAcao : List[ETpAcoes] = {

    if (this.tipotransacao.equals(ETipoTransacao.Compra)) {

       this.status match {
        case EstatusAdministrativo.AguardandoAprovacao => List()
        case EstatusAdministrativo.Aprovado => List()
        case EstatusAdministrativo.EmProcessodeCompra => List(ETpAcoes.Cancelar)
        case EstatusAdministrativo.VendaAutorizada => List(ETpAcoes.Pagar,ETpAcoes.Cancelar)
        case EstatusAdministrativo.AguardandoAvaliacao => List(ETpAcoes.Cancelar,ETpAcoes.Estornar)
        case EstatusAdministrativo.Estornado => List()
        case EstatusAdministrativo.Reprovado => List()
        case EstatusAdministrativo.Cancelado => List()
        case EstatusAdministrativo.Bloqueio => List(ETpAcoes.Estornar)
        case EstatusAdministrativo.Concluida => List()
        case EstatusAdministrativo.Excluida => List()

      }
    } else {
      if (this.tipotransacao.equals(ETipoTransacao.Venda)) {

         this.status match {
          case EstatusAdministrativo.AguardandoAprovacao => List(ETpAcoes.Excluir)
          case EstatusAdministrativo.Aprovado => List(ETpAcoes.Excluir)
          case EstatusAdministrativo.EmProcessodeCompra => List(ETpAcoes.AutorizarVenda,ETpAcoes.Cancelar,ETpAcoes.Reprovar)
          case EstatusAdministrativo.VendaAutorizada => List(ETpAcoes.Cancelar)
          case EstatusAdministrativo.AguardandoAvaliacao => List(ETpAcoes.Estornar)
          case EstatusAdministrativo.Estornado => List()
          case EstatusAdministrativo.Reprovado => List()
          case EstatusAdministrativo.Cancelado => List()
          case EstatusAdministrativo.Bloqueio => List(ETpAcoes.Estornar)
          case EstatusAdministrativo.Concluida => List()
          case EstatusAdministrativo.Excluida => List()
        }

      } else {

         this.status match {
          case EstatusAdministrativo.AguardandoAprovacao => List(ETpAcoes.Aprovar,ETpAcoes.Reprovar,ETpAcoes.Excluir)
          case EstatusAdministrativo.Aprovado => List(ETpAcoes.Excluir,ETpAcoes.Bloqueiar)
          case EstatusAdministrativo.EmProcessodeCompra => List(ETpAcoes.Bloqueiar,ETpAcoes.Cancelar)
          case EstatusAdministrativo.VendaAutorizada => List(ETpAcoes.Bloqueiar,ETpAcoes.Cancelar)
          case EstatusAdministrativo.AguardandoAvaliacao => List(ETpAcoes.Aprovar,ETpAcoes.Reprovar,ETpAcoes.Estornar)
          case EstatusAdministrativo.Estornado => List()
          case EstatusAdministrativo.Reprovado => List(ETpAcoes.Estornar)
          case EstatusAdministrativo.Cancelado => List()
          case EstatusAdministrativo.Bloqueio => List()
          case EstatusAdministrativo.Concluida => List()
          case EstatusAdministrativo.Excluida => List()

        }

      }
    }

  }

  def getDescAcao(yacao : ETpAcoes) : String  = {


     yacao match {
      case ETpAcoes.Aprovar => "Aprovar"
      case ETpAcoes.AutorizarVenda => "Autorizar Venda"
      case ETpAcoes.Bloqueiar => "Bloquear Transação"
      case ETpAcoes.Cancelar => "Cancelar"
      case ETpAcoes.Concluir => "Concluir Transação"
      case ETpAcoes.Estornar => "Extornar"
      case ETpAcoes.Excluir => "Excluir"
      case ETpAcoes.Pagar => "Cancelada"
      case ETpAcoes.Reprovar => "Reprovar Comprador"

    }




  }

  //def getAcoes: List




  def serialize(): JsObject = {
    Json.obj(
      "codigo"-> this.status.toString,
      "nome"-> this.getDesc
    )

  }



}





class  LstCartasEscritorio(ylista :List[Carta], ytp : ETipoTransacao) extends xDevSerialize {
  val ptBr = new Locale("pt", "BR")
  val numberFormat = NumberFormat.getInstance(ptBr)


  def serialize(): JsObject = {
    Json.obj(
      "lista"->
        this.ylista.map { t=>
          Json.obj(
            "codigo"-> t.friendlyID.toString,
            "valordoBem"-> NumberFormat.getCurrencyInstance(ptBr).format(t.valorCredito),
            "tipo"-> Json.obj("codigo"-> t.tipoCarta.uuid, "nome"->t.tipoCarta.name),
            "administradora"-> Json.obj("codigo"-> t.administradora.uuid, "nome"->t.administradora.name),
            "status"-> new TStatusCartaAdm(t.statusCartaAdm,ytp).serialize(),
            "acoes"-> new TStatusCartaAdm(t.statusCartaAdm,ytp).getAcao.map(u=>
              Json.obj(
              "codigo"-> u.toString,
              "nome"->  new TStatusCartaAdm(t.statusCartaAdm,ytp).getDescAcao(u)
              )
            )

          ) }
    )
  }

//  {
//    "codigo": "1",
//    "valorDoBem": "80000",
//    "tipo": {
//      "codigo": "1",
//      "nome": "Rodobens"
//    },
//    "administradora": {
//      "codigo": "1",
//      "nome": "Carro"
//    },
//    "status": {
//      "codigo": "1",
//      "nome": "Aguardando Documentação"
//    }
//  },

}
