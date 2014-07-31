package dao;

import models.Cadastro.AlterarDadosInfo;
import models.Cadastro.RegistrationInfo;
import models.User;
import scala.Option;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserDAO extends AbstractDAO<models.User> {

    public UserDAO() {
        super(models.User.class);
    }

    public models.User create(securesocial.core.Identity i, boolean save) {
        models.User o = new models.User();
        o.email = i.email().get();
        o.providerId = i.identityId().providerId();
        if (i.fullName() != null) {
            o.realName = i.fullName();
        } else if (i.firstName() != null) {
            o.realName = i.firstName();
        } else {
            o.realName = i.identityId().userId();
        }




        if (save) save(o);

        return o;
    }

    public User findbyemailandprovider(String email, String provider)
    {

        for(User xuser : this.findMany("email",email))
        {
            if(xuser.equals(provider))
            {
                return xuser;
            }else
            {
                return null;

            }
        }


        return null;
    }


    public  void  completarCadastro(Option<String> mail,Option<String> providerId ,Option <RegistrationInfo> form)
    {
        if (!form.isEmpty() && !mail.isEmpty() && !providerId.isEmpty() ){
            models.User user = (new IdentityDAO()).findOneByEmailAndProvider(mail.get(),providerId.get()).user();

            if  (user!= null) {

                //em.getTransaction().begin();

                RegistrationInfo info = form.get();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    user.birthDate = formatter.parse(info.birthDate());
                } catch (ParseException e) {

                    em.getTransaction().rollback();

                }

                user.numDocFederal =info.docFederal();

                if (!info.rg().isEmpty()) {
                    user.numRg = info.rg().get();
                }

                if (!info.numCep().isEmpty()) {
                    user.numCep =  info.numCep().get();
                }


                if (!info.uf().isEmpty()) {
                    user.siglaUf = info.uf();
                }

                if (!info.cidade().isEmpty()) {
                    user.nomeCidade = info.cidade();
                }

                if (!info.bairro().isEmpty()) {
                    user.nomeBairro = info.bairro();
                }

                if (!info.logradouro().isEmpty()) {
                    user.nomeLogradouro = info.bairro();
                }

                if (!info.numLogradouro().isEmpty()) {
                    user.numLogradouro = info.numLogradouro();
                }


                user.codigoPlano = info.numCodigo();


                save(user);


            }



        }

    }


    public  void alterarCadastro(Option <AlterarDadosInfo> form, String email, String providerId)
    {
        if (!form.isEmpty() && !email.isEmpty() ){
            models.User user = (new IdentityDAO()).findOneByEmailAndProvider(email,providerId).user();
            if  (user!= null) {



                AlterarDadosInfo info = form.get();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                user.realName = info.firstName();

                try {
                    user.birthDate = formatter.parse(info.birthDate());
                } catch (ParseException e) {

                    em.getTransaction().rollback();

                }

                user.numDocFederal = info.docFederal();

                if (!info.rg().isEmpty()) {
                    user.numRg = info.rg().get();
                }

                if (!info.numCep().isEmpty()) {
                    user.numCep = info.numCep().get();
                }


                if (!info.uf().isEmpty()) {
                    user.siglaUf = info.uf();
                }

                if (!info.cidade().isEmpty()) {
                    user.nomeCidade = info.cidade();
                }

                if (!info.bairro().isEmpty()) {
                    user.nomeBairro = info.bairro();
                }

                if (!info.logradouro().isEmpty()) {
                    user.nomeLogradouro =info.logradouro();
                }

                if (!info.numLogradouro().isEmpty()) {
                    user.numLogradouro = info.numLogradouro();
                }

                save(user);


            }







        }



    }


    public  boolean verificaCadastro(String  email, String providerId ){
        models.User xUser = (new IdentityDAO()).findOneByEmailAndProvider(email,providerId).user();
        boolean xreturn  = true;
        if (xUser != null){


            if (xUser.birthDate == null
                    || xUser.realName == null
                    || xUser.birthDate == null
                    || xUser.numDocFederal == null
                    || xUser.siglaUf == null
                    || xUser.nomeCidade == null
                    || xUser.nomeBairro == null
                    || xUser.nomeLogradouro == null
                    || xUser.numLogradouro == null

                    ){
                return false;
            }


        }


        return xreturn;
    }


    public  boolean verificanumCodigo(String  email, String providerId){
        models.User xUser = (new IdentityDAO()).findOneByEmailAndProvider(email,providerId).user();
        boolean xreturn  = true;
        if (xUser != null){


            if (xUser.codigoAcesso.equals(null) ){
                return false;
            }


        }


        return xreturn;
    }

    public  boolean verificanumCodigoDigitado(String email,String providerId , String pass){
        models.User xUser = (new IdentityDAO()).findOneByEmailAndProvider(email,providerId).user();
        boolean xreturn  = true;
        if (xUser != null){

            if (xUser.codigoAcesso.equals(null)){
                // primeira vez nao ira validar pq esta vazio
                return true;
            }else{
                try {
                    return xUser.codigoAcesso.equals(util.MD5.hash(pass))?true:false;

                }catch (NoSuchAlgorithmException e)
                {
                    return false;
                }
            }



        }


        return xreturn;
    }


    public boolean AlteranumCodigo(String email, String providerId , String pass){
        models.User xUser = (new IdentityDAO()).findOneByEmailAndProvider(email,providerId).user();
        if (xUser != null){

          xUser.codigoPlano = pass;
          save(xUser);
          return true;

        }else{
            return false;
        }

    }

}
