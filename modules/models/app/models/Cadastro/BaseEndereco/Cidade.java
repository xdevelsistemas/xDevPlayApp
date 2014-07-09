package models.Cadastro.BaseEndereco;
import models.AbstractModel;
import javax.persistence.*;

/**
 * Created by claytonsantosdasilva on 05/06/14.
 */

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"ID_UF","NAME"})
})
public class Cidade extends AbstractModel {

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    @Column(name = "NAME")
    private String _name;

    public Uf get_Uf() {
        return _uf;
    }

    public void set_Uf(Uf uf) {
        this._uf = uf;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_UF")
    private Uf _uf;










}
