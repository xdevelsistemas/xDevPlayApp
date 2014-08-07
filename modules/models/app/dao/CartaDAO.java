package dao;

import models.Proconsorcio.Carta;
import models.Proconsorcio.EstatusCarta;
import models.Proconsorcio.RestModels.Pesquisa;
import models.Proconsorcio.RestModels.ResultadoPesquisa;

import javax.persistence.criteria.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by claytonsantosdasilva on 04/08/14.
 *
 * criado somente para fazer ponte para a classe CartaDAOextend
 */
public class CartaDAO extends AbstractDAO<Carta> {
    public CartaDAO() {
        super(Carta.class);
    }

    public ResultadoPesquisa PesquisaCarta(Pesquisa yobj){
        javax.persistence.criteria.CriteriaQuery<Carta> cq = cb.createQuery(Carta.class);
        Root<Carta> root = cq.from(Carta.class);
        List<Predicate> predicates = new ArrayList<>();

//        var administradora: Option[UUID] = None
//        var contemplacao: Option[EstatusCarta] = None
//        var prazo_restante: String = new String
//        var tipo: Option[UUID] = None
//        var valor_credito_max: Option[Number] = None
//        var valor_credito_min: Option[Number] = None
//        var valor_parcelas_max: Option[Number] = None
//        var valor_parcelas_min: Option[Number] = None
//        var itens_pagina: Int = 5
//        var pagina: Int = 1
//        var ordem: String = "asc"
//        var ordenador: String = ""


        if (yobj.administradora().isDefined()){
            predicates.add(cb.equal(root.get("administradora"), (yobj.administradora().get())));
        }

        if (yobj.tipo().isDefined()){
            predicates.add(cb.equal(root.get("tipoCarta"), (yobj.tipo().get())));
        }

        if (!yobj.contemplacao().isEmpty()){
           predicates.add(cb.equal(root.get("statusCarta"), EstatusCarta.valueOf(yobj.contemplacao()) ));

        }

        if (!yobj.prazo_restante().isEmpty()){
             String[] operators = yobj.prazo_restante().split("|");

            for (String op : operators){
                if (op.length() == 2){
                    if (op.charAt(0) == '_'){
                        try{

                            Expression<Number> prazo = root.get("prazoRestante");
                            Number n = NumberFormat.getInstance().parse(String.valueOf(op.charAt(1)));
                            predicates.add(cb.le(prazo,n));


                        }catch (Exception e){}
                    }else{
                        try{

                            Expression<Number> prazo = root.get("prazoRestante");
                            Number n = NumberFormat.getInstance().parse(String.valueOf(op.charAt(0)));
                            predicates.add(cb.ge(prazo,n));


                        }catch (Exception e){}

                    }



                }

            }


        }

        if (yobj.valor_credito_min().isDefined()){
            Expression<Number> prazo = root.get("valorCredito");
            predicates.add(cb.ge(prazo,yobj.valor_credito_min().get()));

        }

        if (yobj.valor_credito_max().isDefined()){
            Expression<Number> prazo = root.get("valorCredito");
            predicates.add(cb.le(prazo,yobj.valor_credito_max().get()));

        }


        if (yobj.valor_parcelas_min().isDefined()){
            Expression<Number> prazo = root.get("valorPrestacao");
            predicates.add(cb.ge(prazo,yobj.valor_parcelas_min().get()));

        }

        if (yobj.valor_parcelas_max().isDefined()){
            Expression<Number> prazo = root.get("valorPrestacao");
            predicates.add(cb.le(prazo,yobj.valor_parcelas_max().get()));

        }

        if (yobj.ordem().equals("asc")){
            if (yobj.ordenador().equals("preco")){
                cq.orderBy(cb.asc(root.get("valorEntrada")));
            }else{
                cq.orderBy(cb.asc(root.get("administradora")));
            }

        }else{
            if (yobj.ordenador().equals("valor_credito")){
                cq.orderBy(cb.asc(root.get("valorCredito")));
            }else if (yobj.ordenador().equals("valor_entrada")){
                cq.orderBy(cb.asc(root.get("valorEntrada")));
            }else{
                cq.orderBy(cb.asc(root.get("valorPrestacao")));
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));


        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cqcount = qb.createQuery(Long.class);
        cqcount.select(qb.count(cqcount.from(Carta.class)));
        cqcount.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        Long _total = em.createQuery(cqcount).getSingleResult();





       return new ResultadoPesquisa(_total,em.createQuery(cq).getResultList());




    }



}
