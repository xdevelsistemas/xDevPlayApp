package dao;

import models.Proconsorcio.*;

/**
 * Created by claytonsantosdasilva on 28/07/14.
 */
public class CartaDAO extends  AbstractDAO<Carta> {

    public CartaDAO() {
        super(Carta.class);
    }

    public Carta findbyFriendlyId(int id)
    {
        return super.findOne("friendlyID",id);

    }
}
