/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.Reservation;
import cz.cvut.fel.rsp.server.Model.Room;
import cz.cvut.fel.rsp.server.Model.User;
import cz.cvut.fel.rsp.server.dao.HotelDao;
import cz.cvut.fel.rsp.server.dao.ReservationDao;
import cz.cvut.fel.rsp.server.dao.RoomDao;
import cz.cvut.fel.rsp.server.dao.UnregisteredUserDao;
import cz.cvut.fel.rsp.server.dao.UserDao;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author FN
 */
@Service
public class RegUserService extends DaoConnection {
    
    public RegUserService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao);
    }
    
    public List<User> findAll() {
        return userDao.findAll();
    }
    
    public void createUser(User u, Hotel h) {
        u.setHotel(h);
        h.getRegisteredUsers().add(u);
        userDao.persist(u);
        hotelDao.update(h);
    }
    
    public void updateUser(User u) {
        userDao.update(u);
    }
    
    public boolean deleteUser(User u) {
        if(!u.isDeleteable()) {
            return false;
        }
        List<Reservation> reservations = u.getReservations();
        Hotel h = u.getHotel();
        for(Reservation r: reservations) {
            List<Room> rooms = r.getRooms();
            for(Room room: rooms) {
                room.getReservations().remove(r);
                roomDao.update(room);
            }
            h.getReservations().remove(r);
            resDao.remove(r);            
        }
        hotelDao.update(h);
        userDao.remove(u);
        return true;
    }
}
