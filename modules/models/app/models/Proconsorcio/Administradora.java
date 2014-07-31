package models.Proconsorcio;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import models.AbstractModel;

import javax.annotation.Nullable;
import javax.persistence.*;

/**
 * Created by claytonsantosdasilva on 21/07/14.
 */

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"name"})
})
public class Administradora extends AbstractModel {
    @Column(nullable = false)
    public String name;

    @Column( nullable = false )
    public boolean ativo;


    @Override
    public void prePersist() {
        super.prePersist();

        this.ativo = true;
    }
}
