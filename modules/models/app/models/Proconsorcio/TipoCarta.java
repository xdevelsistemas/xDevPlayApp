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
        @UniqueConstraint(columnNames={"name"})
})
public class TipoCarta extends AbstractModel {
    @Column
    public String name;

    @Column(nullable = false )
    public boolean ativo;

    @Override
    public void prePersist()
    {
        super.prePersist();

        this.ativo = true;
    }


}
