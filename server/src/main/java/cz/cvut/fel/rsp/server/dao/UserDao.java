/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.dao;

import cz.cvut.fel.rsp.server.Model.User;

/**
 *
 * @author FN
 */
public class UserDao extends AbstractDao<User> {
    
    public UserDao() {
        super(User.class);
    }
    
}
