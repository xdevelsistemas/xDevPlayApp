package models.Cadastro

import _root_.java.util.{Date, UUID}

import com.typesafe.plugin._
import org.joda.time.DateTime
import play.api.Play.current
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraint
import play.api.i18n.Messages
import play.api.mvc._
import play.api.templates.Html
import play.api.{Logger, Play}
import securesocial.core.providers.utils._
import securesocial.core.providers.{Token, UsernamePasswordProvider}
import scala.language.reflectiveCalls

/**
 * Created by claytonsantosdasilva on 06/07/14.
 */
case class RegistrationInfo( userName: Option[String],
                             firstName: String,
                             birthDate:String,
                             password: String,
                             numCep :Option[String],
                             uf:String,
                             cidade:String,
                             bairro:String,
                             logradouro:String,
                             numLogradouro:String,
                             rg:Option[String],
                             docFederal:String,
                             numCodigo:String)

case class AlterarDadosInfo( uuid:Option[String],
                             firstName: String,
                             birthDate:String,
                             numCep :Option[String],
                             uf:String,
                             cidade:String,
                             bairro:String,
                             logradouro:String,
                             numLogradouro:String,
                             rg:Option[String],
                             docFederal:String)


object  RegistrationObjects extends Object {
  val uuid     = "uuid"
  val UserName = "userName"
  val FirstName = "firstName"
  val LastName = "lastName"
  val Password = "password"
  val Password1 = "password1"
  val Password2 = "password2"
  val Email = "email"
  val Success = "success"
  val Error = "error"

  // campos personalizados
  val BirthDate = "birthDate"
  val NumCep = "numCep"
  val Uf = "uf"
  val Cidade = "cidade"
  val Bairro = "bairro"
  val Logradouro = "logradouro"
  val NumLogradouro = "numLogradouro"
  val Rg = "rg"
  val DocFederal = "docFederal"
  val NumCodigo = "numCodigo"
  val NumCodigo1 = "numCodigo1"
  val NumCodigo2 = "numCodigo2"

  val formRegistration = Form[RegistrationInfo](
    mapping(
      UserName ->  text,
      FirstName -> nonEmptyText,
      BirthDate -> nonEmptyText,
      Password -> text,
      NumCep -> text ,
      Uf -> nonEmptyText ,
      Cidade -> nonEmptyText ,
      Bairro -> nonEmptyText ,
      Logradouro -> nonEmptyText ,
      NumLogradouro -> nonEmptyText ,
      Rg -> text ,
      DocFederal -> nonEmptyText ,
      NumCodigo -> text
    )
      // binding
      ((
        userName,
        firstName,
        birthDate,
        password,
        numCep,
        uf,
        cidade,
        bairro,
        logradouro,
        numLogradouro,
        rg,
        docFederal,
        numCodigo

         ) => RegistrationInfo(
        None,
        firstName,
        birthDate,
        password,
        Some(numCep),
        uf,
        cidade,
        bairro,
        logradouro,
        numLogradouro,
        Some(rg),
        docFederal,
        numCodigo
      )
        )
      // unbinding
      (info =>
        Some(
          info.userName.getOrElse(""),
          info.firstName,
          info.birthDate,
          "",
          info.numCep.getOrElse(""),
          info.uf,
          info.cidade,
          info.bairro,
          info.logradouro,
          info.numLogradouro,
          info.rg.getOrElse(""),
          info.docFederal,
          ""
        ))
  )


  val formAlterarDados = Form[AlterarDadosInfo](
    mapping(
      uuid -> text,
      FirstName -> nonEmptyText,
      BirthDate -> nonEmptyText,
      NumCep -> text ,
      Uf -> nonEmptyText ,
      Cidade -> nonEmptyText ,
      Bairro -> nonEmptyText ,
      Logradouro -> nonEmptyText ,
      NumLogradouro -> nonEmptyText ,
      Rg -> text ,
      DocFederal -> nonEmptyText
    )
      // binding
      ((
        uuid,
         firstName,
         birthDate,
         numCep,
         uf,
         cidade,
         bairro,
         logradouro,
         numLogradouro,
         rg,
         docFederal

         ) => AlterarDadosInfo(
        Some(uuid),
        firstName,
        birthDate,
        Some(numCep),
        uf,
        cidade,
        bairro,
        logradouro,
        numLogradouro,
        Some(rg),
        docFederal
      )
        )
      // unbinding
      (info =>
        Some(
          info.uuid.getOrElse(""),
          info.firstName,
          info.birthDate,
          info.numCep.getOrElse(""),
          info.uf,
          info.cidade,
          info.bairro,
          info.logradouro,
          info.numLogradouro,
          info.rg.getOrElse(""),
          info.docFederal
        ))
  )

}
