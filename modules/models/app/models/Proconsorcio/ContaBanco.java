package models.Proconsorcio;

import models.AbstractModel;
import models.User;

import javax.persistence.*;

/**
 * Created by claytonsantosdasilva on 02/08/14.
 */

@Entity
public class ContaBanco extends AbstractModel{

    @Column
    public String agencia;

    @Column
    public String conta;

    @Column
    public String numBanco;

    @Column
    public String nomeBanco;


    @Column
    public Boolean padrao;



    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn
    public User usuario;



    @Column( nullable = false )
    public boolean ativo;


    @Override
    public void prePersist() {
        super.prePersist();

        this.ativo = true;
    }

}
