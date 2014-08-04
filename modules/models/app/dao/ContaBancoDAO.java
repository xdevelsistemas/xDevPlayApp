package dao;

import models.Proconsorcio.RestModels.ContaBancoForm;
import models.Proconsorcio.ContaBanco;
import models.User;
import util.TpResponse;

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

        ArrayList<PairQuery> filter = new ArrayList<PairQuery>();
        filter.add(new PairQuery<UUID>("usuario", user_uuid));
        filter.add(new PairQuery<Boolean>("ativo", true));

        return findMany("usuario", new UserDAO().findOne(user_uuid));

    }

    public List<ContaBanco> findAllbyUser(User user) {
//        ArrayList<PairQuery> filter = new ArrayList<>();
//        filter.add(new PairQuery<String>("usuario", user.uuid));
//        filter.add(new PairQuery<Boolean>("ativo", true));
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
        ArrayList<ContaBanco> xresult = new ArrayList<>();
        for (ContaBanco item : lista_usuario) {
            if (item.ativo && item.padrao) {
                xresult.add(item);
            }

        }

        return xresult;

    }


    public ContaBancoForm add(ContaBanco cta, User user) throws Exception {
        final ContaBancoDAO dao = new ContaBancoDAO();
        ContaBancoForm frm = new ContaBancoForm();
        cta.usuario = user;
        try {

            if (findAllbyUser(user).isEmpty()) {
                cta.padrao = true;
                return frm.read(dao.save(cta), new TpResponse("1", ""));
            } else {
                if (cta.padrao) {

                    if (!removePadrao(user)) {
                        throw new Exception("Erro ao remover Padrão das demais contas");
                    }
                }

                return frm.read(dao.save(cta), new TpResponse("1", ""));

            }

        } catch (Exception e) {
            throw new Exception("Erro ao inserir Conta", e);
        }


    }


    private boolean removePadrao(User user) {
        ContaBancoDAO dao = new ContaBancoDAO();
        try {
            for (ContaBanco outrasctas : findAllDefaultbyUser(user)) {
                outrasctas.padrao = false;
                dao.save(outrasctas);
            }

        } catch (Exception e) {
            return false;
        }

        return true;

    }


    public TpResponse setPadrao(UUID reg, User user) throws Exception{
        try {

            ContaBancoDAO dao = new ContaBancoDAO();
            ContaBanco cta = findOne(reg);

            for (ContaBanco outrasctas : findAllDefaultbyUser(user)) {
                outrasctas.padrao = false;
                dao.save(outrasctas);
            }

            cta.padrao = true;

            dao.save(cta);
            return new TpResponse("1", "");
        } catch (Exception e) {
            throw new Exception("Erro ao definir conta como Padrão", e);
        }


    }


}
