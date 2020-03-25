/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.rest;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.Model.Room;
import cz.cvut.fel.rsp.server.service.HotelService;
import cz.cvut.fel.rsp.server.service.RegUserService;
import cz.cvut.fel.rsp.server.service.ReservationService;
import cz.cvut.fel.rsp.server.service.RoomService;
import cz.cvut.fel.rsp.server.service.UnregUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FN
 */
@RestController
@RequestMapping("/rest/hotel")
@PreAuthorize("permitAll()")
public class HotelServiceRest extends AbstractServices{
    
    @Autowired
    public HotelServiceRest(RoomService roomService, HotelService hotelService, RegUserService regUserService, ReservationService reservationService, UnregUserService unregUserService) {
        super(roomService, hotelService, regUserService, reservationService, unregUserService);
    }
    
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelById(@PathVariable Integer id) {
        return hotelService.findOneById(id);
    }
}
