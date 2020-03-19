/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.Room;
import cz.cvut.fel.rsp.server.dao.HotelDao;
import cz.cvut.fel.rsp.server.dao.ReservationDao;
import cz.cvut.fel.rsp.server.dao.RoomDao;
import cz.cvut.fel.rsp.server.dao.UnregisteredUserDao;
import cz.cvut.fel.rsp.server.dao.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FN
 */
@Service
public class RoomService extends DaoConnection {
    
    @Autowired
    public RoomService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao);
    }
    
    public List<Room> getAllRooms() {
        return roomDao.findAll();
    }
    
    public Room findRoomById(int id) {
        return roomDao.find(id);
    }
    
    public void addRoom(Room r) {
        if(r != null) {
            roomDao.persist(r);
        }
    }
    
    public void updateRoom(Room r) {
        if(roomDao.exists(r.getId())) {
            roomDao.update(r);
        }
    }
    
    public void deleteRoom(Room r) {
        if(roomDao.exists(r.getId())) {
            roomDao.remove(r);
        }
    }
    
}
