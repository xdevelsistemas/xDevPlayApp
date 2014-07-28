package models.Proconsorcio;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import models.AbstractModel;

import javax.persistence.*;

/**
 * Created by claytonsantosdasilva on 21/07/14.
 */

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"_name"})
})
public class Administradora extends AbstractModel {
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



    @PrePersist
    @Override
    public void prePersist() {
        super.prePersist();

        this.set_ativo(true);
    }
}
