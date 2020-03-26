/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.dao;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.Room;
import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FN
 */
@Repository
public class RoomDao extends AbstractDao<Room> {
    
    public RoomDao() {
        super(Room.class);
    }
    
    public List<Room> findAllByHotel(Hotel h) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Room> r  = cq.from(Room.class);
        Expression<Collection<Hotel>> hotels = r.get("hotel");
        Predicate p = cb.isMember(h, hotels);
        cq.where(p);

        return em.createQuery(cq).getResultList();
    }
    
}
