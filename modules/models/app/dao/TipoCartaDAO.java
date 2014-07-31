package dao;

import models.Proconsorcio.TipoCarta;

/**
 * Created by claytonsantosdasilva on 29/07/14.
 */

public class TipoCartaDAO extends AbstractDAO<TipoCarta> {

        public TipoCartaDAO() {
            super(TipoCarta.class);
        }

        public TipoCarta findByName(String name){
            return super.findOne("name",name);
        }


}

