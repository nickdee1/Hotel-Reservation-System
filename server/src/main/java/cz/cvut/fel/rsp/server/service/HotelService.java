/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

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
public class HotelService extends DaoConnection {

    @Autowired
    public HotelService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao, PasswordEncoder passwordEncoder) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao, passwordEncoder);
    }    

    @Transactional
    public List<Hotel> getAllHotels() {
        return hotelDao.findAll();
    }

    @Transactional
    public Hotel findOneById(int id) {
        return hotelDao.find(id);
    }

    @Transactional
    public boolean update(Hotel h) {
        Hotel old = hotelDao.find(h.getId());
        if (old != null) {
            h.setRegisteredUsers(old.getRegisteredUsers());
            h.setReservations(old.getReservations());
            h.setRooms(old.getRooms());
            hotelDao.update(h);
            return true;
        }
        return false;
    }

    @Transactional
    public void addHotel(Hotel h) {
        hotelDao.persist(h);
    }

}
