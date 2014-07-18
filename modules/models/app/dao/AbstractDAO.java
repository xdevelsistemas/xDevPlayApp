package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import models.AbstractModel;
import play.db.jpa.JPA;


public class AbstractDAO<T extends AbstractModel> {

    protected final Class<T> classType;
    protected final T metaclass;
    protected final EntityManager em;
    protected final CriteriaBuilder cb;




    protected AbstractDAO(Class<T> classType) {
        EntityManager temp_em;


        try {
            temp_em = JPA.em();
        }catch (Exception e)
        {
            temp_em = JPA.em("default");
        }


        this.em = temp_em;
        this.cb = em.getCriteriaBuilder();
        this.classType = classType;
        try {
            this.metaclass = classType.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public T findOne(UUID uuid) {
        CriteriaQuery<T> cq = cb.createQuery(classType);
        Root<T> root = cq.from(classType);
        cq.where(cb.equal(root.get(metaclass.uuid), uuid));
        try {
            T result = em.createQuery(cq).getSingleResult();
            return result;
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

    public <A> T findOne(String field, A value) {
        CriteriaQuery<T> cq = cb.createQuery(classType);
        Root<T> root = cq.from(classType);
        cq.where(cb.equal(root.get(field), value));
        try {
            T result = em.createQuery(cq).getSingleResult();
            return result;
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

    public <A> List<T> findMany(String field, A value) {
        CriteriaQuery<T> cq = cb.createQuery(classType);
        Root<T> root = cq.from(classType);
        cq.where(cb.equal(root.get(field), value));
        List<T> results = em.createQuery(cq).getResultList();
        return results;
    }

    public <A> List<T> all() {
        CriteriaQuery<T> cq = cb.createQuery(classType);
        Root<T> root = cq.from(classType);
        List<T> results = em.createQuery(cq).getResultList();
        return results;
    }

    public T save(T o) {
        if (o.uuid != null) {
            return em.merge(o);
        } else {
            em.persist(o);
            return o;
        }


    }

    public void delete(UUID uuid) {
        T o = findOne(uuid);
        if (o != null) em.remove(o);
    }

}