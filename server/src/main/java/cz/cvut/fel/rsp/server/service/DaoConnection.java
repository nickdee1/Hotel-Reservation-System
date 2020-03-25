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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FN
 */
@Service
public abstract class DaoConnection {
    protected HotelDao hotelDao;
    protected UserDao userDao;
    protected ReservationDao resDao;
    protected UnregisteredUserDao unregUserDao;
    protected RoomDao roomDao;

    @Autowired
    public DaoConnection(HotelDao hotelDao, UserDao userDao, ReservationDao resDao, UnregisteredUserDao unregUserDao, RoomDao roomDao) {
        this.hotelDao = hotelDao;
        this.userDao = userDao;
        this.resDao = resDao;
        this.unregUserDao = unregUserDao;
        this.roomDao = roomDao;
        init();
    }
    
    private void init() {
        if(hotelDao.find(1) != null) {
            return;
        }
        Hotel h = new Hotel();
        h.setName("Dream Team Hotel");
        h.setCapacity(500);
        h.setCity("Dream Town");
        h.setCountry("United Dream States");
        h.setEmail("dream@hotel.cz");
        h.setId(1);
        h.setPhoneNum("+420111222333");
        h.setPostalCode(56567);
        h.setStreetName("Dream Street");
        hotelDao.persist(h);
    }
    
    
    
}
