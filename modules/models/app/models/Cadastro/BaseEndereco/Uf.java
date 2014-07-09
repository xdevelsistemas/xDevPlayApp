package models.Cadastro.BaseEndereco;
import models.AbstractModel;

import javax.persistence.*;

/**
 * Created by claytonsantosdasilva on 05/06/14.
 */

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"SIGLA"})
})
public class Uf extends AbstractModel {

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    @Column(name = "NAME")
    private String _name;

    public String get_sigla() {
        return _sigla;
    }

    @Column(name = "SIGLA")
    public void set_sigla(String _sigla) {
        this._sigla = _sigla;
    }

    private String _sigla;

}
