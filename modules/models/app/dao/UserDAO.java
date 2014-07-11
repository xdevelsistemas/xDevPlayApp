package dao;

import models.Cadastro.AlterarDadosInfo;
import models.Cadastro.RegistrationInfo;
import models.Identity;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import scala.Option;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class UserDAO extends AbstractDAO<models.User> {

    public UserDAO() {
        super(models.User.class);
    }

    public models.User create(securesocial.core.Identity i, boolean save) {
        models.User o = new models.User();
        o.email = i.email().get();
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



    //TODO corrigir as persistencias utilizando classe anonima JPA.withTransaction
    public  void  completarCadastro(Option<String> mail,Option <RegistrationInfo> form)
    {
        if (!form.isEmpty() && !mail.isEmpty()){
            models.User user = findOne("email",mail.get());
            if  (user!= null) {

                //em.getTransaction().begin();

                RegistrationInfo info = form.get();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    user.set_birthDate(formatter.parse(info.birthDate()));
                } catch (ParseException e) {

                    em.getTransaction().rollback();

                }

                user.set_numDocFederal(info.docFederal());

                if (!info.rg().isEmpty()) {
                    user.set_numRg(info.rg().get());
                }

                if (!info.numCep().isEmpty()) {
                    user.set_numCep(info.numCep().get());
                }


                if (!info.uf().isEmpty()) {
                    user.set_siglaUf(info.uf());
                }

                if (!info.cidade().isEmpty()) {
                    user.set_nomeCidade(info.cidade());
                }

                if (!info.bairro().isEmpty()) {
                    user.set_nomeBairro(info.bairro());
                }

                if (!info.logradouro().isEmpty()) {
                    user.set_nomeLogradouro(info.logradouro());
                }

                if (!info.numLogradouro().isEmpty()) {
                    user.set_numLogradouro(info.numLogradouro());
                }



                user.set_codigoAcesso(info.numCodigo());


                //em.persist(user);



                save(user);





            }



        }

    }

    //TODO corrigir as persistencias utilizando classe anonima JPA.withTransaction
    public  void alterarCadastro(Option <AlterarDadosInfo> form, String email)
    {
        if (!form.isEmpty() && !email.isEmpty() ){
            models.User user = findOne("email", email);
            if  (user!= null) {



                AlterarDadosInfo info = form.get();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                user.realName = info.firstName();

                try {
                    user.set_birthDate(formatter.parse(info.birthDate()));
                } catch (ParseException e) {

                    em.getTransaction().rollback();

                }

                user.set_numDocFederal(info.docFederal());

                if (!info.rg().isEmpty()) {
                    user.set_numRg(info.rg().get());
                }

                if (!info.numCep().isEmpty()) {
                    user.set_numCep(info.numCep().get());
                }


                if (!info.uf().isEmpty()) {
                    user.set_siglaUf(info.uf());
                }

                if (!info.cidade().isEmpty()) {
                    user.set_nomeCidade(info.cidade());
                }

                if (!info.bairro().isEmpty()) {
                    user.set_nomeBairro(info.bairro());
                }

                if (!info.logradouro().isEmpty()) {
                    user.set_nomeLogradouro(info.logradouro());
                }

                if (!info.numLogradouro().isEmpty()) {
                    user.set_numLogradouro(info.numLogradouro());
                }

                save(user);


            }







        }



    }


    public  boolean verificaCadastro(String  email){
        models.User xUser = findOne("email",email);
        boolean xreturn  = true;
        if (xUser != null){


            if (xUser.get_birthDate() == null
                    || xUser.realName == null
                    || xUser.get_birthDate() == null
                    || xUser.get_numDocFederal() == null
                    || xUser.get_siglaUf() == null
                    || xUser.get_nomeCidade() == null
                    || xUser.get_nomeBairro() == null
                    || xUser.get_nomeLogradouro() == null
                    || xUser.get_numLogradouro() == null

                    ){
                return false;
            }


        }


        return xreturn;
    }


    public  boolean verificanumCodigo(String  email){
        models.User xUser = findOne("email",email);
        boolean xreturn  = true;
        if (xUser != null){


            if (xUser.get_codigoAcesso() == null){
                return false;
            }


        }


        return xreturn;
    }

    public  boolean verificanumCodigoDigitado(String email, String pass){
        models.User xUser = findOne("email",email);
        boolean xreturn  = true;
        if (xUser != null){

            if (xUser.get_codigoAcesso() == null){
                // primeira vez nao ira validar pq esta vazio
                return true;
            }else{
                try {
                    return xUser.get_codigoAcesso().equals(util.MD5.hash(pass))?true:false;

                }catch (NoSuchAlgorithmException e)
                {
                    return false;
                }
            }



        }


        return xreturn;
    }

    //TODO corrigir as persistencias utilizando classe anonima JPA.withTransaction
    public boolean AlteranumCodigo(String email, String pass){
        models.User xUser = findOne("email",email);
        if (xUser != null){

          xUser.set_codigoAcesso(pass);
          save(xUser);
          return true;

        }else{
            return false;
        }

    }

}
