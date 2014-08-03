package models.Proconsorcio;

import models.AbstractModel;
import models.User;

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
    @JoinColumn(nullable = false)
    @NotNull(message="Campo 'id Amigável' não pode ser nulo")
    public Sequenciador friendlyID;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    public EstatusAdministrativo statusCartaAdm;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    public EstatusCarta statusCarta;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_tipocarta", nullable = false)
    @NotNull(message="Campo 'Tipo de Carta' não pode ser nulo")
    public TipoCarta tipoCarta;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn( nullable = false)
    @NotNull(message="Campo 'Administradora' não pode ser nulo")
    public Administradora administradora;

    @Column(nullable = false)
    @NotNull(message="Campo 'prazo Restante' não pode ser nulo")
    public Integer prazoRestante;


    @Column(nullable = false, precision = 2)
    @NotNull(message="Campo 'valor Crédito' não pode ser nulo")
    public float valorCredito;

    @Column(nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Entrada' não pode ser nulo")
    public float valorEntrada;

    @Column(nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Prestação' não pode ser nulo")
    public float valorPrestacao;

    @Column(nullable = false, precision = 2)
    @NotNull(message="Campo 'Valor Cota' não pode ser nulo")
    public float valorCota;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    public User usuario;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_usuario_aprovacao")
    public User usuarioAprovacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date horaAprovacao;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_usuario_cancelamento")
    public User usuarioCancelamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column()
    public Date horaCancelamento;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_usuario_exlclusao")
    public User usuarioExclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date horaExclusao;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_usuario_bloqueio")
    public User usuarioBloqueio;


    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date horaBloqueio;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_usuario_compra")
    public User usuarioCompra;


    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date horaCompra;


    @Column
    public String bancoDeposito;

    @Column
    public String agenciaDeposito;

    @Column
    public String contaDeposito;




    @Column
    public String avaliacaoComprador;



    @PrePersist
    @Override
    public void prePersist() {
        super.prePersist();

        this.statusCartaAdm = EstatusAdministrativo.AguardandoAprovacao;
    }


}
