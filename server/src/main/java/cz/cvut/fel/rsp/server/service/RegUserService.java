/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.Enums.UserRoleEnum;
import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.Reservation;
import cz.cvut.fel.rsp.server.Model.Room;
import cz.cvut.fel.rsp.server.Model.User;
import cz.cvut.fel.rsp.server.Model.*;
import cz.cvut.fel.rsp.server.Model.Enums.UserRoleEnum;
import cz.cvut.fel.rsp.server.dao.HotelDao;
import cz.cvut.fel.rsp.server.dao.ReservationDao;
import cz.cvut.fel.rsp.server.dao.RoomDao;
import cz.cvut.fel.rsp.server.dao.UnregisteredUserDao;
import cz.cvut.fel.rsp.server.dao.UserDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FN
 */
@Service
public class RegUserService extends DaoConnection {

    @Autowired
    public RegUserService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao, PasswordEncoder passwordEncoder) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao, passwordEncoder);
    }

    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findUserById(int id) { return userDao.find(id); }

    @Transactional
    public boolean createUser(User u, int hotelId) {
        Hotel h = hotelDao.find(hotelId);
        if(h == null) {
            return false;
        }
        u.setHotel(h);
        h.getRegisteredUsers().add(u);
        userDao.persist(u);
        hotelDao.update(h);
        return true;
    }

    @Transactional
    public boolean updateUser(User u) {
        User old = userDao.find(u.getId());
        if (old != null) {
            u.setReservations(old.getReservations());
            u.setRoles(u.getRoles());
            u.setHotel(old.getHotel());
            userDao.update(u);
            return true;
        }
        return false;
    }

    @Transactional
    public List<Reservation> getAllReservations(Integer id) {
        User user = userDao.find(id);
        if (user != null) {
            return user.getReservations();
        }
        return null;
    }


    @Transactional
    public boolean updateUserRoles(int id, List<UserRoleEnum> roles) {
        User u = userDao.find(id);
        if(u != null) {
            u.setRoles(roles);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteUser(int id) {
        User u = userDao.find(id);
        if (u == null) {
            return false;
        }
        if (!u.isDeleteable()) {
            return false;
        }
        List<Reservation> reservations = u.getReservations();
        Hotel h = u.getHotel();
        for (Reservation r : reservations) {
            List<Room> rooms = r.getRooms();
            for (Room room : rooms) {
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
