/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.*;
import cz.cvut.fel.rsp.server.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author FN
 */
@Service
public class UnregUserService extends DaoConnection {


    @Autowired
    public UnregUserService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao);
    }

    public List<UnregisteredUser> getAllUnregUsers() { return unregUserDao.findAll(); }

    public UnregisteredUser findUserById(int id) { return unregUserDao.find(id); }

    @Transactional
    public void updateUser(UnregisteredUser unregisteredUser) {
        if (unregUserDao.exists(unregisteredUser.getId())) {
            unregUserDao.update(unregisteredUser);
        }
    }

    /* Redesign */
    @Transactional
    public void createUser(UnregisteredUser unregisteredUser, List<Reservation> reservations) {
        unregisteredUser.setReservations(reservations);
        unregUserDao.persist(unregisteredUser);
    }

    @Transactional
    public boolean deleteUser(UnregisteredUser unregisteredUser) {
        if(!unregisteredUser.isDeleteable()) {
            return false;
        }
        List<Reservation> reservations = unregisteredUser.getReservations();
        for(Reservation r: reservations) {
            Hotel h = r.getHotel();
            List<Room> rooms = r.getRooms();
            for(Room room: rooms) {
                room.getReservations().remove(r);
                roomDao.update(room);
            }
            h.getReservations().remove(r);
            resDao.remove(r);
            hotelDao.update(h);
        }
        unregUserDao.remove(unregisteredUser);
        return true;
    }


}
