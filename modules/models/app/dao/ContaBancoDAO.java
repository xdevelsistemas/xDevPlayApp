package dao;

import RestModels.ContaBancoForm;
import models.Proconsorcio.Carta;
import models.Proconsorcio.ContaBanco;
import models.User;
import play.db.jpa.JPA;
import play.libs.F;
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
        List<ContaBanco> lista_usuario = findMany("usuario",user);

        for(ContaBanco item: lista_usuario)
        {
            if(!item.ativo){
                lista_usuario.remove(item);
            }
        }

        return lista_usuario;

    }

    private List<ContaBanco> findAllDefaultbyUser(User user) {
        List<ContaBanco> lista_usuario = findAllbyUser(user);

        for(ContaBanco item: lista_usuario)
        {
            if(!item.ativo || !item.padrao){
                lista_usuario.remove(item);
            }

        }

        return lista_usuario;

    }


    public ContaBancoForm add(final ContaBanco cta, User user) {
        final ContaBancoDAO dao = new ContaBancoDAO();
        ContaBancoForm frm = new ContaBancoForm();
        cta.usuario = user;


        if (findAllbyUser(user).isEmpty()) {
            cta.padrao = true;
        } else {
            if (cta.padrao) {
                for (final ContaBanco outrasctas : findAllDefaultbyUser(user)) {
                    if (outrasctas.padrao) {

                        try {
                            outrasctas.padrao = false;
                            dao.save(outrasctas);
                        } catch (Exception e) {
                            return frm.read(cta, new TpResponse("0", e.getMessage()));
                        }
                    }
                }
            }
        }




        try {
            return frm.read(dao.save(cta),new TpResponse("1",""));
        }catch (Exception e)
        {
            return frm.read(cta,new TpResponse("0",e.getMessage()));
        }



    }

    public TpResponse setPadrao (UUID reg, User user)
    {
        final ContaBancoDAO dao = new ContaBancoDAO();
        final ContaBanco cta = findOne(reg);

        if (findAllbyUser(user).isEmpty()) {
            cta.padrao = true;
        } else {
            if (cta.padrao) {
//                ArrayList<PairQuery> filters = new ArrayList<PairQuery>();
//                filters.add(new PairQuery<Boolean>("ativo", true));
//                filters.add(new PairQuery<Boolean>("padrao", true));

                for (final ContaBanco outrasctas : findAllDefaultbyUser(user)) {
                    outrasctas.padrao =  false;
                    dao.save(outrasctas);
                }
            }
        }


        dao.save(cta);
        return new TpResponse("1", "");

    }



}
