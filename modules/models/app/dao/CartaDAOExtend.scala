package dao

import java.{util, lang}
import java.util.{Date, UUID}
import javax.persistence.criteria
import javax.persistence.criteria.Predicate.BooleanOperator
import javax.persistence.criteria._
import _root_.util.{TpResponse, xDevForm}
import models.User
import models.Proconsorcio._
import models.Proconsorcio.RestModels.{ResultadoPesquisa, Pesquisa, CartaForm}
import org.eclipse.persistence.sessions.Session
import org.hibernate.criterion.{Criterion, Restrictions}
import org.hibernate.jpa.criteria.CriteriaBuilderImpl
import play.api.mvc.SimpleResult
import play.libs.F

//import org.hibernate.jpa.
import play.db.jpa.JPA
import scala.collection.JavaConverters._

/**
 * Created by claytonsantosdasilva on 04/08/14.
 */
/**
 * Created by claytonsantosdasilva on 28/07/14.
 */
class CartaDAOextend extends CartaDAO {
  def findbyFriendlyId(id: Long): Carta = {
    return super.findOne("friendlyID", (new SequenciadorDAO).findOne(id))
  }


  private def isValidForm(yobj: CartaForm) : Boolean = {
    if(yobj != null && yobj.status.result.equals("1")){
      return true
    }

    return false
  }





  private def nextStatus(yCarta : Carta, yUser : User , yAcao : ETpAcoes)  : EstatusAdministrativo = {



      val status = yCarta.statusCartaAdm
      var tpOper : ETipoTransacao = ETipoTransacao.Compra
      if (yCarta.usuario == yUser){
        tpOper = ETipoTransacao.Venda
      }else{
        if(yUser.isAdmin){
          tpOper = ETipoTransacao.Gerenciar
        }else{
          tpOper = ETipoTransacao.Compra
        }
      }

      val restStatusCarta = (new RestModels.TStatusCartaAdm(status,tpOper))
      if (restStatusCarta.getAcao.filter(p=>p == yAcao).isEmpty){
        throw  new Exception("O status da Carta não permite determinada Operação " + "Operação[" + restStatusCarta.getDescAcao(yAcao) + "]" )
      }else{
        //TODO toda regra de negocio é passado por aqui
        EstatusAdministrativo.Aprovado
      }

  }



  def setStatus(id : Long, yUser: User, yAcao : ETpAcoes, yJustificativa : String) : TpResponse = {
     try{




       val daohist = new CartaHistoricoDAO
       val carta = findbyFriendlyId(id)

       if (carta == null){
         new TpResponse("0","Carta não encontrada")
       }else
       {

         val hist = new CartaHistorico
         val statusNovo = nextStatus(carta,yUser,yAcao)

         hist.carta = carta

         hist.usuarioEvento = yUser
         hist.horaevento = new Date()
         hist.justificativa = yJustificativa
         hist.statusCartaAdmAntes = carta.statusCartaAdm
         hist.statusCartaAdm = statusNovo
         hist.usuarioComprador = carta.usuarioCompra
         carta.ultimoEvento =  daohist.save(hist)
         save(carta)


       }







       new TpResponse("1","")

     }catch{
       case e: Exception => throw new Exception("não foi possível alterar o status da carta")
     }

  }

  /*
  def add(yobj : Carta) : CartaForm = {
    try {

      /*
      *  como se trata de duas tabelas foi criado duas persistencias distintas
      *
      * */

      JPA.withTransaction("default", false, new F.Function0[Unit] {

        def apply: Unit = {
          val xseq = (new SequenciadorDAO()).save(new Sequenciador)
          yobj.friendlyID = xseq

        }
      })

      JPA.withTransaction("default", false, new F.Function0[Unit] {

        def apply: Unit = {
          (new CartaDAO).save(yobj)

        }
      })



      val carta =  (new CartaForm)
        .read(yobj,(new TpResponse("1",
        "Sucesso, você pode agora acompanhar sua carta no escritório online")))

      return carta

    }catch {
      case e: Exception => throw new Exception("não foi possível inserir uma carta")
    }



  }*/





}