package dao

import java.util
import java.util.UUID
import _root_.util.{TpResponse, xDevForm}
import models.User
import models.Proconsorcio.{Sequenciador, Carta}
import models.Proconsorcio.RestModels.CartaForm

/**
 * Created by claytonsantosdasilva on 04/08/14.
 */
/**
 * Created by claytonsantosdasilva on 28/07/14.
 */
class CartaDAOextend extends CartaDAO {
  def findbyFriendlyId(id: Int): Carta = {
    return super.findOne("friendlyID", id)
  }


  private def isValidForm(yobj: CartaForm) : Boolean = {
    if(yobj != null && yobj.status.result.equals("1")){
      return true
    }

    return false
  }

  def add(yobj : Carta, user : User) : CartaForm = {
    try {

      val xseq = (new SequenciadorDAO()).save(new Sequenciador)

      yobj.friendlyID = xseq
      yobj.usuario = user
      save(yobj)

      val carta =  (new CartaForm)
        .read(yobj,(new TpResponse("1",
        "Sucesso, você pode agora acompanhar sua carta no escritório online")))

      return carta

    }catch {
      case e: Exception => throw new Exception("não foi possível inserir uma carta")
    }



  }





}