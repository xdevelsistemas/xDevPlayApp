package models.Proconsorcio;

import dao.ContaBancoDAO;
import models.AbstractModel;
import models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by claytonsantosdasilva on 02/08/14.
 */

@Entity
public class ContaBanco extends AbstractModel{

    @Column(name="AGENCIA",nullable = false)
    public String agencia;

    @Column(name="CONTA",nullable = false)
    public String conta;

    @Column(name="NUM_BANCO",nullable = false)
    public String numBanco;

    @Column(name="NOME_BANCO",nullable = false)
    public String nomeBanco;


    @Column(name="PADRAO",nullable = false)
    public Boolean padrao;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @NotNull(message="Campo 'Usuário' não pode ser nulo")
    public User usuario;



    @Column(name="ATIVO",nullable = false)
    public boolean ativo;


    @Override
    public void prePersist() {
        super.prePersist();

/************ resolver o problema da pesquisa com filtros multiplos
        if ((new ContaBancoDAO()).findMany("id_usuario",this.usuario).isEmpty()){

        }
*************/
        this.ativo = true;
    }

}
