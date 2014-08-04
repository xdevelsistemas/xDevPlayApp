package models;
import models.Cadastro.BaseEndereco.Cidade;
import models.Cadastro.BaseEndereco.Logradouro;
import models.Cadastro.BaseEndereco.Uf;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"email","providerId"})
})
public class User extends AbstractModel {

    @NotNull(message="Campo 'email' não pode ser nulo")
    public String email;

    @NotNull(message="Campo 'Nome' não pode ser nulo")
    public String realName;

    @NotNull(message="Campo 'Nome' não pode ser nulo")
    public String providerId;

    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDATE")
    public java.util.Date birthDate;

    @Column(name="NUM_RG")
    public String numRg;


    @Column(name="NUM_DOC_FEDERAL")
    public String numDocFederal;

    @Column(name="NUM_CEP")
    public String numCep;

    @Column(name="LOGRADOURO")
    public String nomeLogradouro;
    @Column(name="NUM_LOGRADOURO")
    public String numLogradouro;
    @Column(name="COMPLEMENTO")
    public String complemento;

    @Column(name="SIGLA_UF")
    public String siglaUf;


    @Column(name="NOME_CIDADE")
    public String nomeCidade;

    @Column(name="NOME_BAIRRO")
    public String nomeBairro;

    public String codigoPlano;

    @Column(name="CODIGO_ACESSO")
    public String codigoAcesso;

    @Column(name="ADMIN")
    public Boolean isadmin;


//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "ID_UF")
//    public Uf uf;


//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "ID_CIDADE")
//    public Cidade cidade;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "ID_LOGRADOURO")
//    public Logradouro logradouro;


    private void setNumCodigo(String plainpass)
    {
        if(plainpass != null) {
            try {
                this.codigoAcesso = util.MD5.hash(plainpass);
            } catch (NoSuchAlgorithmException ex) {
                this.codigoAcesso = null;
            }
        }
    }




    @Override
    public void prePersist() {
        super.prePersist();

        setNumCodigo(this.codigoPlano);



    }

    @Override
    public void preUpdate() {
        super.preUpdate();

        setNumCodigo(this.codigoPlano);

    }




}
