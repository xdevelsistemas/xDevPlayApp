package models;

import models.Cadastro.BaseEndereco.Cidade;
import models.Cadastro.BaseEndereco.Logradouro;
import models.Cadastro.BaseEndereco.Uf;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"email"})
})
public class User extends AbstractModel {

    @NotNull(message="Campo 'email' não pode ser nulo")
    public String email;

    @NotNull(message="Campo 'Nome' não pode ser nulo")
    public String realName;

    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDATE")
    private java.util.Date _birthDate;

    @Column(name="NUM_RG")
    private String _numRg;

    @Column(name="UF_RG")
    private String _ufRg;



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
    @Column(name="NOME_CIDADE")
    private String _nomeCidade;

    @Column(name="NOME_BAIRRO")
    private String _nomeBairro;


    @Column(name="NUM_BANCO")
    private String _numBanco;



    @Column(name="NUM_AGENCIA")
    private String _numAgencia;

    @Column(name="DV_AGENCIA")
    private String _dvAgencia;

    @Column(name="NUM_CONTA")
    private String _numConta;

    @Column(name="DV_CONTA")
    private String _dvConta;

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

    public String get_ufRg() {
        return _ufRg;
    }

    public void set_ufRg(String _ufRg) {
        this._ufRg = _ufRg;
    }


    public String get_numBanco() {
        return _numBanco;
    }

    public void set_numBanco(String _numBanco) {
        this._numBanco = _numBanco;
    }

    public String get_numAgencia() {
        return _numAgencia;
    }

    public void set_numAgencia(String _numAgencia) {
        this._numAgencia = _numAgencia;
    }

    public String get_dvAgencia() {
        return _dvAgencia;
    }

    public void set_dvAgencia(String _dvAgencia) {
        this._dvAgencia = _dvAgencia;
    }

    public String get_numConta() {
        return _numConta;
    }

    public void set_numConta(String _numConta) {
        this._numConta = _numConta;
    }

    public String get_dvConta() {
        return _dvConta;
    }

    public void set_dvConta(String _dvConta) {
        this._dvConta = _dvConta;
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

    public void set_numCep(String _numCep) {
        this._numCep = _numCep;
    }

    public String get_nomeLogradouro() {
        return _nomeLogradouro;
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
