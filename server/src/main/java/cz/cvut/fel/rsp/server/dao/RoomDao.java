/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.dao;

import cz.cvut.fel.rsp.server.Model.Room;
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
    
}
