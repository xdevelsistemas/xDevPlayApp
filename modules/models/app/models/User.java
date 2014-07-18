package models;
import models.Cadastro.RegistrationInfo;
import play.db.jpa.JPA;
import scala.Option;
import models.Cadastro.BaseEndereco.Cidade;
import models.Cadastro.BaseEndereco.Logradouro;
import models.Cadastro.BaseEndereco.Uf;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private java.util.Date _birthDate;

    @Column(name="NUM_RG")
    private String _numRg;


    @Column(name="NUM_DOC_FEDERAL")

    private String _numDocFederal;




    @Column(name="NUM_CEP")
    private String _numCep;
    @Column(name="LOGRADOURO")
    private String _nomeLogradouro;
    @Column(name="NUM_LOGRADOURO")
    private String _numLogradouro;
    @Column(name="COMPLEMENTO")
    private String _complemento;



    @Column(name="SIGLA_UF")
    private String _siglaUf;


    @Column(name="NOME_CIDADE")
    private String _nomeCidade;

    @Column(name="NOME_BAIRRO")
    private String _nomeBairro;



    @Column(name="CODIGO_ACESSO")
    private String _codigoAcesso;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_UF")
    private Uf _uf;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_CIDADE")
    private Cidade _cidade;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_LOGRADOURO")
    private Logradouro _logradouro;


    public String get_numDocFederal() {
        return _numDocFederal;
    }

    public void set_numDocFederal(String _numDocFederal) {
        this._numDocFederal = _numDocFederal;
    }





    public Date get_birthDate() {
        return _birthDate;
    }

    public void set_birthDate(Date _birthDate) {
        this._birthDate = _birthDate;
    }

    public String get_numRg() {
        return _numRg;
    }

    public void set_numRg(String _numRg) {
        this._numRg = _numRg;
    }

    public String get_numCep() {
        return _numCep;
    }

    public String get_siglaUf() {
        return _siglaUf;
    }

    public void set_siglaUf(String _siglaUf) {
        this._siglaUf = _siglaUf;
    }

    public String get_nomeLogradouro() {
        return _nomeLogradouro;
    }

    public void set_numCep(String _numCep) {
        this._numCep = _numCep;
    }

    public void set_nomeLogradouro(String _nomeLogradouro) {
        this._nomeLogradouro = _nomeLogradouro;
    }

    public String get_numLogradouro() {
        return _numLogradouro;
    }

    public void set_numLogradouro(String _numLogradouro) {
        this._numLogradouro = _numLogradouro;
    }

    public String get_complemento() {
        return _complemento;
    }

    public void set_complemento(String _complemento) {
        this._complemento = _complemento;
    }

    public String get_nomeCidade() {
        return _nomeCidade;
    }

    public void set_nomeCidade(String _nomeCidade) {
        this._nomeCidade = _nomeCidade;
    }


    public String get_nomeBairro() {
        return _nomeBairro;
    }

    public void set_nomeBairro(String _nomeBairro) {
        this._nomeBairro = _nomeBairro;
    }

    public String get_codigoAcesso() {
        return _codigoAcesso;
    }

    public void set_codigoAcesso(String _codigoAcesso) {

        try{
            this._codigoAcesso = util.MD5.hash(_codigoAcesso);
        }catch (NoSuchAlgorithmException ex)
        {
            this._codigoAcesso  = null;
        }


    }

    public Uf get_uf() {
        return _uf;
    }

    public void set_uf(Uf _uf) {
        this._uf = _uf;
    }

    public Cidade get_cidade() {
        return _cidade;
    }

    public void set_cidade(Cidade _cidade) {
        this._cidade = _cidade;
    }

    public Logradouro get_logradouro() {
        return _logradouro;
    }

    public void set_logradouro(Logradouro _logradouro) {
        this._logradouro = _logradouro;
    }


}
