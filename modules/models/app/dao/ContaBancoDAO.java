package dao;

import models.Proconsorcio.RestModels.ContaBancoForm;
import models.Proconsorcio.ContaBanco;
import models.User;
import net.sf.ehcache.search.expression.Criteria;
import org.hibernate.Session;
import util.TpResponse;

import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by claytonsantosdasilva on 02/08/14.
 * <p/>
 * Classe responsável pelo processamento das contas
 */
public class ContaBancoDAO extends AbstractDAO<ContaBanco> {

    public ContaBancoDAO() {
        super(ContaBanco.class);
    }

    public List<ContaBanco> findAllbyUser(UUID user_uuid) {


        return findMany("usuario", new UserDAO().findOne(user_uuid));

    }

    public List<ContaBanco> findAllbyUser(User user) {
        List<ContaBanco> lista_usuario = findMany("usuario", user);

        for (ContaBanco item : lista_usuario) {
            if (!item.ativo) {
                lista_usuario.remove(item);
            }
        }

        return lista_usuario;

    }

    private ArrayList<ContaBanco> findAllDefaultbyUser(User user) {
        List<ContaBanco> lista_usuario = findAllbyUser(user);
        ArrayList<ContaBanco> xresult = new ArrayList<ContaBanco>();
        for (ContaBanco item : lista_usuario) {
            if (item.ativo && item.padrao) {
                xresult.add(item);
            }

        }

        return xresult;

    }


    public ContaBancoForm add(ContaBanco cta, User user) throws Exception {
        ContaBancoForm frm = new ContaBancoForm();
        cta.usuario = user;
        try {


            if (findAllbyUser(user).isEmpty()) {
                cta.padrao = true;
                return frm.read(save(cta), new TpResponse("1", ""));
            } else {
                if (cta.padrao) {

                    if (!removePadrao(user)) {
                        throw new Exception("Erro ao remover Padrão das demais contas");
                    }
                }

                return frm.read((new ContaBancoDAO()).save(cta), new TpResponse("1", ""));

            }

        } catch (Exception e) {
            throw new Exception("Erro ao inserir Conta", e);
       }


    }


    private boolean removePadrao(User user) {
        try {
            for (ContaBanco outrasctas : findAllDefaultbyUser(user)) {
                outrasctas.padrao = false;
                save(outrasctas);
            }

        } catch (Exception e) {
            return false;
        }

        return true;

    }


    public TpResponse setPadrao(UUID reg, User user) throws Exception{
        try {


            ContaBanco cta = findOne(reg);

            for (ContaBanco outrasctas : findAllDefaultbyUser(user)) {
                outrasctas.padrao = false;
                save(outrasctas);
            }

            cta.padrao = true;

            save(cta);
            return new TpResponse("1", "");
        } catch (Exception e) {
            throw new Exception("Erro ao definir conta como Padrão", e);
        }


    }


}
