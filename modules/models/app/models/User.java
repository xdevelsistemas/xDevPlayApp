package models;

import models.Cadastro.BaseEndereco.Cidade;
import models.Cadastro.BaseEndereco.Logradouro;
import models.Cadastro.BaseEndereco.Uf;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.impl.auth.UnsupportedDigestAlgorithmException;
import sun.security.provider.MD5;

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

    public String get_codigoAcesso() {
        return _codigoAcesso;
    }

    public void set_codigoAcesso(String _codigoAcesso) {

        this._codigoAcesso = DigestUtils.md5Hex(_codigoAcesso);
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



}
