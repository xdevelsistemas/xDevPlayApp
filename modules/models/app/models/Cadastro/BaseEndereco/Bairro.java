package models.Cadastro.BaseEndereco;
import models.AbstractModel;
import javax.persistence.*;

/**
 * Created by claytonsantosdasilva on 05/06/14.
 */

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"ID_CIDADE","NAME"})
})
public class Bairro extends AbstractModel {

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    @Column(name = "NAME")
    private String _name;

    public Cidade get_cidade() {
        return _cidade;
    }

    public void set_cidade(Cidade cidade) {
        this._cidade = cidade;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_CIDADE")
    private Cidade _cidade;










}
