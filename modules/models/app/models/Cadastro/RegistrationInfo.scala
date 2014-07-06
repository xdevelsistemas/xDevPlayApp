package models.Cadastro

/**
 * Created by claytonsantosdasilva on 06/07/14.
 */
case class RegistrationInfo( userName: Option[String],
                             firstName: String,
                             birthDate:String,
                             password: String,
                             numCep :Option[String],
                             uf:Option[String],
                             cidade:Option[String],
                             bairro:Option[String],
                             logradouro:Option[String],
                             numLogradouro:Option[String],
                             rg:Option[String],
                             //ufRg:Option[String],
                             docFederal:String,
                             numBanco:Option[String],
                             numAgencia:Option[String],
                             //dvAgencia:Option[String],
                             numConta:Option[String],
                             //dvConta:Option[String],
                             numCodigo:String)
