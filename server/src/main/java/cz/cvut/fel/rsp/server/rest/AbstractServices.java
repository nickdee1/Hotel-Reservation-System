/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.rest;

import cz.cvut.fel.rsp.server.service.HotelService;
import cz.cvut.fel.rsp.server.service.RegUserService;
import cz.cvut.fel.rsp.server.service.ReservationService;
import cz.cvut.fel.rsp.server.service.RoomService;
import cz.cvut.fel.rsp.server.service.UnregUserService;
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
    
}
