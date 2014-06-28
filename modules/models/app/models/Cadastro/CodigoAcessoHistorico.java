package models.Cadastro;

/**
 * Created by claytonsantosdasilva on 25/06/14.
 */

import  models.Base.*;
import models.User;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by claytonsantosdasilva on 25/06/14.
 */
public class CodigoAcessoHistorico extends xDevEntity {
// os codigos de acesso a principio serão guardados em md5

    public String get_codigoAcesso() {
        return _codigoAcesso;
    }

    public void set_codigoAcesso(String _codigoAcesso) {
        this._codigoAcesso = _codigoAcesso;
    }

    public Date get_horageracao() {
        return _horageracao;
    }

    public void set_horageracao(Date _horageracao) {
        this._horageracao = _horageracao;
    }

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }

    @Column(name="NUM_CODIGO")
    private String _codigoAcesso;
    @Column(name="HORA_GERACAO")
    private Date _horageracao;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "USER_MAIL")
    private User _user;

}