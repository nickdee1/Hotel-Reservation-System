/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.service;

import cz.cvut.fel.rsp.server.Model.Hotel;
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
public class HotelService extends DaoConnection {

    @Autowired
    public HotelService(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao) {
        super(hotelDao, userDao, resDao, unregUserDao, roomDao);
    }

    public List<Hotel> getAllHotels() {
        return hotelDao.findAll();
    }
    
    public Hotel findOneById(int id) {
        return hotelDao.find(id);
    }
    
    public void update(Hotel h) {
        hotelDao.update(h);
    }
    
    public void addHotel(Hotel h) {
        hotelDao.persist(h);
    }
    
    public void deleteHotel(Hotel h) {
        hotelDao.remove(h);
    }

}
