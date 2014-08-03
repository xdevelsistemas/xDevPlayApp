package dao;

import models.Proconsorcio.Administradora;
import models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by claytonsantosdasilva on 29/07/14.
 */
public class AdministradoraDAO extends AbstractDAO<Administradora> {

    public AdministradoraDAO() {
        super(Administradora.class);
    }

    public Administradora findByName(String name){
        return super.findOne("name",name);
    }

    public List<Administradora> findAllValid()
    {
        ArrayList<PairQuery> filter = new ArrayList<PairQuery>();
        filter.add(new PairQuery<Boolean>("ativo",true));

        return findMany(filter);


    }


}
