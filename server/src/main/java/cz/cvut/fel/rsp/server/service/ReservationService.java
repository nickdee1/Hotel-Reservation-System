/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.Reservation;
import cz.cvut.fel.rsp.server.Model.UnregisteredUser;
import cz.cvut.fel.rsp.server.Model.User;
import cz.cvut.fel.rsp.server.dao.HotelDao;
import cz.cvut.fel.rsp.server.dao.ReservationDao;
import cz.cvut.fel.rsp.server.dao.RoomDao;
import cz.cvut.fel.rsp.server.dao.UnregisteredUserDao;
import cz.cvut.fel.rsp.server.dao.UserDao;
import java.util.List;
import javax.transaction.Transactional;

import cz.cvut.fel.rsp.server.service.Model.ResUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author FN
 */
@Service
public class ReservationService extends DaoConnection {

    @Autowired
    public ReservationService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao, PasswordEncoder passwordEncoder) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao, passwordEncoder);
    }  
    
    @Transactional
    public List<Reservation> getAllRes() {
        return resDao.findAll();
    }
    
    @Transactional
    public Reservation findResById(int id) {
        return resDao.find(id);
    }
    
    @Transactional
    public boolean updateReservation(Reservation reservation) {
        Reservation old = resDao.find(reservation.getId());
        if (old != null) {
            reservation.setHotel(old.getHotel());
            reservation.setRegUser(old.getRegUser());
            reservation.setUnregUser(old.getUnregUser());
            reservation.setRooms(old.getRooms());
            resDao.update(reservation);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean addReservationReg(Reservation reservation, Integer userId, Integer hotelid) {
        User user = userDao.find(userId);
        Hotel hotel = hotelDao.find(hotelid);
        if (reservation != null && user != null && hotel != null) {
            user.setReservation(reservation);
            reservation.setRegUser(user);
            reservation.setHotel(hotel);
            hotel.addReservation(reservation);
            hotelDao.update(hotel);
            resDao.persist(reservation);
            userDao.update(user);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean addReservationUnreg(Reservation reservation, Integer userId, Integer hotelid) {
        UnregisteredUser user = unregUserDao.find(userId);
        Hotel hotel = hotelDao.find(hotelid);
        if (reservation != null && user != null && hotel != null) {
            user.setReservation(reservation);
            reservation.setUnregUser(user);
            reservation.setHotel(hotel);
            hotel.addReservation(reservation);
            resDao.persist(reservation);
            hotelDao.update(hotel);
            unregUserDao.update(user);
            return true;
        }
        return false;
    }

    @Transactional
    public List<Reservation> getReservationsByHotel(Integer hotelid) {
        Hotel hotel = hotelDao.find(hotelid);
        if (hotel != null) {
            return hotel.getReservations();
        }
        return null;
    }

    @Transactional
    public ResUser getUserByReservation(Integer id) {
        Reservation reservation = resDao.find(id);
        ResUser resUser = new ResUser();

        if (reservation == null) return null;
        if (reservation.getRegUser() != null) {
            resUser.setRegistered(reservation.getRegUser());
            return resUser;
        }
        else if (reservation.getUnregUser() != null) {
            resUser.setUnregistered(reservation.getUnregUser());
            return resUser;
        }
        return null;
    }

}
