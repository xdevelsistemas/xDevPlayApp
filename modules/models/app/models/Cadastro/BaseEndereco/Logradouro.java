package models.Cadastro.BaseEndereco;
import models.AbstractModel;

import javax.persistence.*;

/**
 * Created by claytonsantosdasilva on 25/06/14.
 */


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"cep"})
})
public class Logradouro  extends AbstractModel {

    @Column(name="LOGRADOURO")
    private String _logradouro;

    @Column(name="TIPO_LOGRADOURO")
    private String _tipoLogradouro;

    public String get_cep() {
        return _cep;
    }

    public void set_cep(String _cep) {
        this._cep = _cep;
    }

    @Column(name="CEP")
    private String _cep;


    public Cidade get_cidade() {
        return _cidade;
    }

    public void set_cidade(Cidade cidade) {
        this._cidade = cidade;
    }

    public Bairro get_bairro() {
        return _bairro;
    }

    public void set_bairro(Bairro bairro) {
        this._bairro = bairro;
    }

    public String get_tipoLogradouro() {
        return _tipoLogradouro;
    }

    public void set_tipoLogradouro(String _tipoLogradouro) {
        this._tipoLogradouro = _tipoLogradouro;
    }

    public String get_logradouro() {
        return _logradouro;
    }

    public void set_logradouro(String _logradouro) {
        this._logradouro = _logradouro;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_CIDADE")
    private Cidade _cidade;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_BAIRRO")
    private Bairro _bairro;

}
