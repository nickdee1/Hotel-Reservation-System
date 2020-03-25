/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.rest;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.service.HotelService;
import cz.cvut.fel.rsp.server.service.RegUserService;
import cz.cvut.fel.rsp.server.service.ReservationService;
import cz.cvut.fel.rsp.server.service.RoomService;
import cz.cvut.fel.rsp.server.service.UnregUserService;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author FN
 */

public abstract class AbstractServices {
    protected RoomService roomService;
    protected HotelService hotelService;
    protected RegUserService regUserService;
    protected ReservationService reservationService;
    protected UnregUserService unregUserService;

    @Autowired
    public AbstractServices(RoomService roomService, HotelService hotelService, RegUserService regUserService, ReservationService reservationService, UnregUserService unregUserService) {
        this.roomService = roomService;
        this.hotelService = hotelService;
        this.regUserService = regUserService;
        this.reservationService = reservationService;
        this.unregUserService = unregUserService;
    }    
    
    @PostConstruct
    @Transactional
    public void init() {
        if(hotelService.findOneById(1) != null) {
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
        hotelService.addHotel(h);
    }
    
    
}
