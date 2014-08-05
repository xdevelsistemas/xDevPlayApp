package models.Proconsorcio;

import models.AbstractModel;
import models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by claytonsantosdasilva on 04/08/14.
 */
@Entity
public class CartaHistorico  extends AbstractModel {

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ID_CARTA", nullable = false)
    @NotNull(message="Campo 'Carta' não pode ser nulo")
    public Carta carta;


    @Enumerated(EnumType.ORDINAL)
    @Column(name="STATUS_CARTA_ADM_ANTES",nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    public EstatusAdministrativo statusCartaAdmAntes;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="STATUS_CARTA_ADM",nullable = false)
    @NotNull(message="Campo 'Status Carta' não pode ser nulo")
    public EstatusAdministrativo statusCartaAdm;


    @Column(name = "JUSTIFICATIVA")
    public String justificativa;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_COMPRA")
    public User usuarioComprador;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO_EVENTO")
    public User usuarioEvento;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HORA_COMPRA")
    public Date horaevento;



}
