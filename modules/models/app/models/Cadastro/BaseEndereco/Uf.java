package models.Cadastro.BaseEndereco;
import  models.Base.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by claytonsantosdasilva on 05/06/14.
 */

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"name"})
})
public class Uf extends xDevEntity {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
