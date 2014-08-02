package br.com.republicavirtual

import _root_.util.xDevSerialize
import com.thoughtworks.xstream.annotations.XStreamAlias
import play.api.libs.json._
import scala.Function1
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import util._
import java.util.Map

@JsonSerialize
@XStreamAlias("webservicecep")
class CepServiceVO extends xDevSerialize {
  def getBairro: String = {
    return bairro
  }

  def setBairro(bairro: String) {
    this.bairro = bairro
  }

  def getCidade: String = {
    return cidade
  }

  def setCidade(cidade: String) {
    this.cidade = cidade
  }

  def getLogradouro: String = {
    return logradouro
  }

  def setLogradouro(logradouro: String) {
    this.logradouro = logradouro
  }

  def getResultado: String = {
    return resultado
  }

  def setResultado(resultado: String) {
    this.resultado = resultado
  }

  def getResultado_txt: String = {
    return resultado_txt
  }

  def setResultado_txt(resultado_txt: String) {
    this.resultado_txt = resultado_txt
  }

  def getTipo_logradouro: String = {
    return tipo_logradouro
  }

  def setTipo_logradouro(tipo_logradouro: String) {
    this.tipo_logradouro = tipo_logradouro
  }

  def getUf: String = {
    return uf
  }

  def setUf(uf: String) {
    this.uf = uf
  }

  def serialize(): JsObject = {
    Json.obj(
      "uf" -> this.getUf,
      "cidade" -> this.getCidade,
      "bairro" -> this.getBairro,
      "tipo_logradouro" -> this.getTipo_logradouro,
      "logradouro" -> this.getLogradouro,
      "resultado" -> this.getResultado,
      "resultado_txt" -> this.getResultado_txt
    )
  }

  @XStreamAlias("uf") private var uf: String = null
  @XStreamAlias("cidade") private var cidade: String = null
  @XStreamAlias("bairro") private var bairro: String = null
  @XStreamAlias("tipo_logradouro") private var tipo_logradouro: String = null
  @XStreamAlias("logradouro") private var logradouro: String = null
  @XStreamAlias("resultado") private var resultado: String = null
  @XStreamAlias("resultado_txt") private var resultado_txt: String = null
}

