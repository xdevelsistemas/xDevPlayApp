package models.Contato

/**
 * Created by claytonsantosdasilva on 05/08/14.
 * Classe de formulário de contato
 */


import play.api.data._
import play.api.data.Forms._
import scala.language.reflectiveCalls

case class Contatoinfo( email: String,
                        name:String,
                        message: String,
                        about:String
                        )

object  ContatoinfoObject extends Object {

  val Email : String = "email"
  val Message : String = "message"
  val Name : String = "name"
  val About : String = "about"


  val formContatoinfo = Form[Contatoinfo](
    mapping(
      Email -> nonEmptyText,
      Name -> nonEmptyText,
      About -> nonEmptyText,
      Message -> nonEmptyText

    )
      // binding
      ((
         email,
         name,
         about,
         message


         ) => Contatoinfo(
        email,
        name,
        about,
        message
      )
        )
      // unbinding
      (info =>
        Some(
          info.email,
          info.name,
          info.about,
          info.message
        ))
  )

}
