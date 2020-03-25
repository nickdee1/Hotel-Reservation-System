/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.Reservation;
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
public class ReservationService extends DaoConnection {
    
    @Autowired
    public ReservationService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao);
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
    public void updateResById(int id) {
        
    }
    
}
