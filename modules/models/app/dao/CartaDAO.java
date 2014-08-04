package dao;

import models.Proconsorcio.Carta;

/**
 * Created by claytonsantosdasilva on 04/08/14.
 *
 * criado somente para fazer ponte para a classe CartaDAOextend
 */
public class CartaDAO extends AbstractDAO<Carta> {
    public CartaDAO() {
        super(Carta.class);
    }

}
