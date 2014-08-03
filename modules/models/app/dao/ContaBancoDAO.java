package dao;

import models.Proconsorcio.ContaBanco;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by claytonsantosdasilva on 02/08/14.
 *
 * Classe responsável pelo processamento das contas
 *
 */
public class ContaBancoDAO extends  AbstractDAO<ContaBanco> {

    public ContaBancoDAO() {
        super(ContaBanco.class);
    }

    public List<ContaBanco> findAllbyUser(UUID user_uuid)
    {

        ArrayList<PairQuery> filter = new ArrayList<PairQuery>();
        filter.add(new PairQuery<UUID>("usuario",user_uuid));
        filter.add(new PairQuery<Boolean>("ativo",true));

        return findMany("usuario", new UserDAO().findOne(user_uuid));

    }

    public List<ContaBanco> findAllbyUser(User user)
    {
        ArrayList<PairQuery> filter = new ArrayList<PairQuery>();
        filter.add(new PairQuery<User>("usuario",user));
        filter.add(new PairQuery<Boolean>("ativo",true));

        return findMany(filter);


    }
}
