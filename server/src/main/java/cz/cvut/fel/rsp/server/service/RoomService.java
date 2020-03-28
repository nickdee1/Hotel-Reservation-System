/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.Enums.MoneyTypeEnum;
import cz.cvut.fel.rsp.server.Model.Enums.RoomType;
import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.Room;
import cz.cvut.fel.rsp.server.dao.HotelDao;
import cz.cvut.fel.rsp.server.dao.ReservationDao;
import cz.cvut.fel.rsp.server.dao.RoomDao;
import cz.cvut.fel.rsp.server.dao.UnregisteredUserDao;
import cz.cvut.fel.rsp.server.dao.UserDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author FN
 */
@Service
public class RoomService extends DaoConnection {

    @Autowired
    public RoomService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao, PasswordEncoder passwordEncoder) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao, passwordEncoder);
    }

    

    @Transactional
    public List<Room> getAllRooms() {
        return roomDao.findAll();
    }
    
    @Transactional
    public List<Room> getAllRoomsInHotel(int hotelId) {
        Hotel h = hotelDao.find(hotelId);
        if(h == null) {
            return null;
        }
        return h.getRooms();
    }

    @Transactional
    public Room findRoomById(int id) {
        return roomDao.find(id);
    }

    @Transactional
    public boolean addRoom(Room r, int hotelId) {
        Hotel h = hotelDao.find(hotelId);
        if (r != null && h != null) {
            r.setHotel(h);
            h.getRooms().add(r);
            roomDao.persist(r);
            hotelDao.update(h);
            return true;
        }
        return false;
    }
    
    @Transactional
    public boolean updateRoom(Room r) {
        Room old = roomDao.find(r.getId());
        if (old != null) {
            r.setHotel(old.getHotel());
            r.setReservations(old.getReservations());
            roomDao.update(r);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteRoom(int id) {
        Room r = roomDao.find(id);
        if (r == null) {
            return false;
        }
        Hotel h = r.getHotel();
        h.getRooms().remove(r);
        roomDao.remove(r);
        hotelDao.update(h);
        return true;
    }

}
