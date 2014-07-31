package dao;

import models.Proconsorcio.Administradora;

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


}
