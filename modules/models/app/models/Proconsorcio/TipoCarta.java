package models.Proconsorcio;

import models.AbstractModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by claytonsantosdasilva on 21/07/14.
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"_name"})
})
public class TipoCarta extends AbstractModel {
    @Column(name = "NAME")
    private String _name;

    @Column(name = "ATIVO" , nullable = false )
    public boolean _ativo;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public boolean is_ativo() {
        return _ativo;
    }

    public void set_ativo(boolean _ativo) {
        this._ativo = _ativo;
    }
}
