package models.Proconsorcio;

import models.AbstractModel;
import models.User;

import javax.lang.model.element.Name;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by claytonsantosdasilva on 21/07/14.
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"friendlyID"})
})
public class Carta extends AbstractModel {

    @OneToOne()
    @JoinColumn(name="FRIENDLY_ID",nullable = false)
    @NotNull(message="Campo 'id Amigável' não pode ser nulo")
    public Sequenciador friendlyID;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="STATUS_CARTA_ADM",nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    public EstatusAdministrativo statusCartaAdm;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="STATUS_CARTA",nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    public EstatusCarta statusCarta;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CARTA", nullable = false)
    @NotNull(message="Campo 'Tipo de Carta' não pode ser nulo")
    public TipoCarta tipoCarta;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ID_ADMINISTRADORA", nullable = false)
    @NotNull(message="Campo 'Administradora' não pode ser nulo")
    public Administradora administradora;

    @Column(name="PRAZO_RESTANTE",nullable = false)
    @NotNull(message="Campo 'prazo Restante' não pode ser nulo")
    public Integer prazoRestante;


    @Column(name="VALOR_CREDITO",nullable = false, precision = 2)
    @NotNull(message="Campo 'valor Crédito' não pode ser nulo")
    public float valorCredito;

    @Column(name="VALOR_ENTRADA",nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Entrada' não pode ser nulo")
    public float valorEntrada;

    @Column(name="VALOR_PRESTACAO",nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Prestação' não pode ser nulo")
    public float valorPrestacao;

    @Column(name="VALOR_COTA", nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Cota' não pode ser nulo")
    public float valorCota;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @NotNull(message="Campo 'Usuário' não pode ser nulo")
    public User usuario;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_APROVACAO")
    public User usuarioAprovacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_APROVACAO")
    public Date horaAprovacao;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_CANCELAMENTO")
    public User usuarioCancelamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_CANCELAMENTO")
    public Date horaCancelamento;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_EXCLUSAO")
    public User usuarioExclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="HORA_EXCLUSAO")
    public Date horaExclusao;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_BLOQUEIO")
    public User usuarioBloqueio;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_BLOQUEIO")
    public Date horaBloqueio;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_COMPRA")
    public User usuarioCompra;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_COMPRA")
    public Date horaCompra;


    @Column(name = "NUM_BANCO_DEPOSITO")
    public String numBancoDeposito;

    @Column(name = "NOME_BANCO_DEPOSITO")
    public String nomeBancoDeposito;

    @Column(name = "AGENCIA_DEPOSITO")
    public String agenciaDeposito;

    @Column(name = "CONTA_DEPOSITO")
    public String contaDeposito;




    @Column(name = "AVALIACAO_COMPRADOR")
    public String avaliacaoComprador;



    @PrePersist
    @Override
    public void prePersist() {
        super.prePersist();

        this.statusCartaAdm = EstatusAdministrativo.AguardandoAprovacao;
    }


}
