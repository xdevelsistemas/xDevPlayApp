package models.Boleto

import java.io.File
import java.math.BigDecimal
import java.util.{Date, UUID}

import dao.CartaDAOextend
import models.Proconsorcio.Carta
import models.User
import org.jrimum.bopepo.Boleto
import org.jrimum.bopepo.view.BoletoViewer
import org.jrimum.domkee.comum.pessoa.endereco.{UnidadeFederativa, Endereco}
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite
import org.jrimum.domkee.financeiro.banco.febraban._
import play.api.Play

import scala.math.BigDecimal.RoundingMode

/**
 * Created by claytonsantosdasilva on 10/08/14.
 */
object Boletos {
  /**
   * Gera o boleto.
   *
   * @param boleto
   */
  def execute(boleto: Boleto, path: String, name: String) {
    execute(boleto, null, path, name)
  }

  def executeComTemplate(boleto: Boleto, template: File, path: String, name: String) {
    execute(boleto, template, path, name)
  }

  private def execute(boleto: Boleto, template: File, path: String, name: String) {
    val viewer: BoletoViewer = new BoletoViewer(boleto)
    if (template != null) {
      viewer.setTemplate(template)
    }
    val boletoPDF: File = viewer.getPdfAsFile(path + "/" + name)
  }


  def geraBoleto(id : String , user: User, caminho: String): String = {
    val userCarta : User = user
    val carta : Carta = (new  CartaDAOextend).findbyFriendlyId(java.lang.Long.parseLong(id))


    if (carta != null && (userCarta.equals(carta.usuario) || userCarta.equals(carta.usuarioCompra) ) ){

      val _name : UUID = UUID.randomUUID()

      val arquivo = _name + id + ".pdf"


      val cedente: Cedente = new Cedente("xDevel Sistemas", "15.537.102/0001-18")
      val contaBancaria: ContaBancaria = new ContaBancaria
      val agencia: Agencia = new Agencia(1234, "0")
      contaBancaria.setAgencia(agencia)
      val itau: CodigoDeCompensacaoBACEN = new CodigoDeCompensacaoBACEN(341)
      val bco: Banco = new Banco(itau, "Itaú")
      contaBancaria.setBanco(bco)
      val numeroDaConta: NumeroDaConta = new NumeroDaConta(1234, "0")
      contaBancaria.setNumeroDaConta(numeroDaConta)
      contaBancaria.setCarteira(new Carteira(1))


      cedente.addContaBancaria(contaBancaria)


      val sacado: Sacado = new Sacado(userCarta.realName, userCarta.numDocFederal)
      val endereco: Endereco = new Endereco
      endereco.setUF(UnidadeFederativa.valueOfSigla(userCarta.siglaUf))
      endereco.setLocalidade(userCarta.nomeCidade)
      endereco.setCep(userCarta.numCep)
      endereco.setBairro(userCarta.nomeBairro)
      endereco.setLogradouro(userCarta.nomeLogradouro)
      endereco.setNumero(userCarta.numLogradouro)
      sacado.addEndereco(endereco)


      val titulo = new Titulo(contaBancaria, sacado, cedente)




      titulo.setNumeroDoDocumento("1")
      titulo.setNossoNumero("1")
      titulo.setDigitoDoNossoNumero("0")



      if  (carta.usuario == userCarta ){

        val _val = carta.valorCredito.multiply(new BigDecimal(0.01)).setScale(2,RoundingMode.HALF_UP)
        titulo.setValor(_val.bigDecimal)


      }else{
        val _val = carta.valorCredito.multiply(new BigDecimal(1.02)).setScale(2,RoundingMode.HALF_UP)
        titulo.setValor(_val.bigDecimal)
      }




      titulo.setDataDoDocumento(new Date)
      titulo.setDataDoVencimento(new Date)
      titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL)
      titulo.setAceite(Aceite.A)


      val boleto: Boleto = new Boleto(titulo)
      boleto.setLocalPagamento("Pagável preferencialmente na Rede Itaú ou em qualquer Banco até o Vencimento.")
      boleto.setInstrucao1("APÓS o Vencimento, Pagável Somente na Rede Itaú.")

      execute(boleto,caminho,arquivo)

      arquivo


    }else
    {
      (new String(""))
    }

  }

}
