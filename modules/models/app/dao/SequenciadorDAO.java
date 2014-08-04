package dao;

import models.Proconsorcio.Sequenciador;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

/**
 * Created by claytonsantosdasilva on 04/08/14.
 */
public class SequenciadorDAO   {
    protected final Class<Sequenciador> classType;
    protected final Sequenciador metaclass;
    protected final EntityManager em;
    protected final CriteriaBuilder cb;




    protected SequenciadorDAO() {
        EntityManager temp_em;

        try {
            temp_em = JPA.em();
        }catch (Exception e)
        {
            temp_em = JPA.em("default");
        }


        this.em = temp_em;
        this.cb = em.getCriteriaBuilder();
        this.classType = Sequenciador.class;
        try {
            this.metaclass = classType.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Sequenciador findOne(Long id) {

        try {
            Sequenciador result = em.find(Sequenciador.class,id);
            return result;
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

//    public <A> Sequenciador findOne(String field, A value) {
//        CriteriaQuery<Sequenciador> cq = cb.createQuery(classType);
//        Root<Sequenciador> root = cq.from(classType);
//        cq.where(cb.equal(root.get(field), value));
//        try {
//            Sequenciador result = em.createQuery(cq).getSingleResult();
//            return result;
//        } catch (javax.persistence.NoResultException e) {
//            return null;
//        }
//    }

//    public <A> List<Sequenciador> findMany(String field, A value) {
//        CriteriaQuery<Sequenciador> cq = cb.createQuery(classType);
//        Root<Sequenciador> root = cq.from(classType);
//        cq.where(cb.equal(root.get(field), value));
//        List<Sequenciador> results = em.createQuery(cq).getResultList();
//        return results;
//    }


//    public  List<Sequenciador> all() {
//        CriteriaQuery<Sequenciador> cq = cb.createQuery(classType);
//        Root<Sequenciador> root = cq.from(classType);
//        List<Sequenciador> results = em.createQuery(cq).getResultList();
//        return results;
//    }

    public Sequenciador save(Sequenciador o) {
        if (o.id != null) {
            return em.merge(o);
        } else {
            em.persist(o);
            return o;
        }


    }

    public void delete(Long id) {
        Sequenciador o = findOne(id);
        if (o != null) {
            em.getTransaction().begin();
            em.remove(o);
            em.flush();
            em.getTransaction().commit();
        }

    }

}
