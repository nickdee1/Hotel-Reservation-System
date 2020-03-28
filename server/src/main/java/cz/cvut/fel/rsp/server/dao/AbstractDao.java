package cz.cvut.fel.rsp.server.dao;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.Room;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractDao<T>{

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> type;

    protected AbstractDao(Class<T> type) {
        this.type = type;
    }

    public T find(Integer id) {
        Objects.requireNonNull(id);
        return em.find(type, id);
    }

    public List<T> findAll() {
        try {
            return em.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void persist(T entity) {
        Objects.requireNonNull(entity);
        try {
            em.persist(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void persist(Collection<T> entities) {
        Objects.requireNonNull(entities);
        if (entities.isEmpty()) {
            return;
        }
        try {
            entities.forEach(this::persist);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public T update(T entity) {
        Objects.requireNonNull(entity);
        try {
            return em.merge(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void remove(T entity) {
        Objects.requireNonNull(entity);
        try {
            final T toRemove = em.merge(entity);
            if (toRemove != null) {
                em.remove(toRemove);
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public boolean exists(Integer id) {
        return id != null && em.find(type, id) != null;
    }
    
    /**
     * use only with RegUser, Reservation and Room
     * @param h
     * @return 
     */
    public List<T> findAllByHotel(Hotel h) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> r  = cq.from(this.type);
        Expression<Collection<Hotel>> hotels = r.get("hotel");
        Predicate p = cb.isMember(h, hotels);
        cq.where(p);
        return em.createQuery(cq).getResultList();
    }
}
