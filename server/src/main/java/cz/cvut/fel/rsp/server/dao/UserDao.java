/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.dao;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.User;
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
public class UserDao extends AbstractDao<User> {
    
    public UserDao() {
        super(User.class);
    }
    
     public User findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<User> r  = cq.from(User.class);
        Expression<String> userEmail = r.get("email");
        Predicate p = cb.equal(userEmail, email);
        cq.where(p);
        return (User) em.createQuery(cq).getSingleResult();
    }
    
}
