package models.Proconsorcio;

import models.AbstractModel;
import models.User;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by claytonsantosdasilva on 21/07/14.
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"_friendlyID"})
})
public class Carta extends AbstractModel {
    public EstatusAdministrativo get_statusCartaAdm() {
        return _statusCartaAdm;
    }

    public void set_statusCartaAdm(EstatusAdministrativo _statusCartaAdm) {
        this._statusCartaAdm = _statusCartaAdm;
    }

    public Date get_horaAprovacao() {
        return _horaAprovacao;
    }

    public void set_horaAprovacao(Date _horaAprovacao) {
        this._horaAprovacao = _horaAprovacao;
    }

    public Date get_horaCancelamento() {
        return _horaCancelamento;
    }

    public void set_horaCancelamento(Date _horaCancelamento) {
        this._horaCancelamento = _horaCancelamento;
    }

    public Date get_horaExclusao() {
        return _horaExclusao;
    }

    public void set_horaExclusao(Date _horaExclusao) {
        this._horaExclusao = _horaExclusao;
    }

    public Date get_horaBloqueio() {
        return _horaBloqueio;
    }

    public void set_horaBloqueio(Date _horaBloqueio) {
        this._horaBloqueio = _horaBloqueio;
    }

    public Date get_horaCompra() {
        return _horaCompra;
    }

    public void set_horaCompra(Date _horaCompra) {
        this._horaCompra = _horaCompra;
    }

    @OneToOne()
    @JoinColumn(name = "FRIENDLYID", nullable = false)
    @NotNull(message="Campo 'id Amigável' não pode ser nulo")
    private Sequenciador _friendlyID;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUSCARTA_ADMIN", nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    private EstatusAdministrativo _statusCartaAdm;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUSCARTA", nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    private EstatusCarta _statusCarta;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_TIPOCARTA", nullable = false)
    @NotNull(message="Campo 'Tipo de Carta' não pode ser nulo")
    private TipoCarta _tipoCarta;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_ADMINISTRADORA", nullable = false)
    @NotNull(message="Campo 'Administradora' não pode ser nulo")
    private Administradora _administradora;

    @Column(name = "PRAZORESTANTE", nullable = false)
    @NotNull(message="Campo 'prazo Restante' não pode ser nulo")
    private Integer _prazoRestante;


    @Column(name = "VALORCREDITO", nullable = false, precision = 2)
    @NotNull(message="Campo 'valor Crédito' não pode ser nulo")
    private float _valorCredito;

    @Column(name = "VALORENTRADA", nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Entrada' não pode ser nulo")
    private float _valorEntrada;

    @Column(name = "VALORPRESTACAO", nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Prestação' não pode ser nulo")
    private float _valorPrestacao;

    @Column(name = "VALORCOTA", nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Cota' não pode ser nulo")
    private float _valorCota;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private User _usuario;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_APROVACAO")
    private User _usuarioAprovacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_APROVACAO")
    private Date _horaAprovacao;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_CANCELAMENTO")
    private User _usuarioCancelamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_CANCELAMENTO")
    private Date _horaCancelamento;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_EXCLUSAO")
    private User _usuarioExclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_EXCLUSAO")
    private Date _horaExclusao;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_BLOQUEIO")
    private User _usuarioBloqueio;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_BLOQUEIO")
    private Date _horaBloqueio;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_COMPRA")
    private User _usuarioCompra;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_COMPRA")
    private Date _horaCompra;

    public Sequenciador get_friendlyID() {
        return _friendlyID;
    }

    public void set_friendlyID(Sequenciador _friendlyID) {
        this._friendlyID = _friendlyID;
    }

    public EstatusCarta get_statusCarta() {
        return _statusCarta;
    }

    public void set_statusCarta(EstatusCarta _statusCarta) {
        this._statusCarta = _statusCarta;
    }

    public TipoCarta get_tipoCarta() {
        return _tipoCarta;
    }

    public void set_tipoCarta(TipoCarta _tipoCarta) {
        this._tipoCarta = _tipoCarta;
    }

    public Administradora get_administradora() {
        return _administradora;
    }

    public void set_administradora(Administradora _administradora) {
        this._administradora = _administradora;
    }

    public Integer get_prazoRestante() {
        return _prazoRestante;
    }

    public void set_prazoRestante(Integer _prazoRestante) {
        this._prazoRestante = _prazoRestante;
    }

    public float get_valorCredito() {
        return _valorCredito;
    }

    public void set_valorCredito(float _valorCredito) {
        this._valorCredito = _valorCredito;
    }

    public float get_valorEntrada() {
        return _valorEntrada;
    }

    public void set_valorEntrada(float _valorEntrada) {
        this._valorEntrada = _valorEntrada;
    }

    public float get_valorPrestacao() {
        return _valorPrestacao;
    }

    public void set_valorPrestacao(float _valorPrestacao) {
        this._valorPrestacao = _valorPrestacao;
    }

    public float get_valorCota() {
        return _valorCota;
    }

    public void set_valorCota(float _valorCota) {
        this._valorCota = _valorCota;
    }

    public User get_usuario() {
        return _usuario;
    }

    public void set_usuario(User _usuario) {
        this._usuario = _usuario;
    }

    public User get_usuarioAprovacao() {
        return _usuarioAprovacao;
    }

    public void set_usuarioAprovacao(User _usuarioAprovacao) {
        this._usuarioAprovacao = _usuarioAprovacao;
    }

    public User get_usuarioCancelamento() {
        return _usuarioCancelamento;
    }

    public void set_usuarioCancelamento(User _usuarioCancelamento) {
        this._usuarioCancelamento = _usuarioCancelamento;
    }

    public User get_usuarioExclusao() {
        return _usuarioExclusao;
    }

    public void set_usuarioExclusao(User _usuarioExclusao) {
        this._usuarioExclusao = _usuarioExclusao;
    }

    public User get_usuarioBloqueio() {
        return _usuarioBloqueio;
    }

    public void set_usuarioBloqueio(User _usuarioBloqueio) {
        this._usuarioBloqueio = _usuarioBloqueio;
    }

    public User get_usuarioCompra() {
        return _usuarioCompra;
    }

    public void set_usuarioCompra(User _usuarioCompra) {
        this._usuarioCompra = _usuarioCompra;
    }

    public String get_avaliacaoComprador() {
        return _avaliacaoComprador;
    }

    public void set_avaliacaoComprador(String _avaliacaoComprador) {
        this._avaliacaoComprador = _avaliacaoComprador;
    }

    @Column(name = "AVALIACAO")
    private String _avaliacaoComprador;



    @PrePersist
    @Override
    public void prePersist() {
        super.prePersist();

        this.set_statusCartaAdm(EstatusAdministrativo.AguardandoAprovacao);
    }


}
