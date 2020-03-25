/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.rsp.server.rest;

import cz.cvut.fel.rsp.server.Model.Room;
import cz.cvut.fel.rsp.server.service.HotelService;
import cz.cvut.fel.rsp.server.service.RegUserService;
import cz.cvut.fel.rsp.server.service.ReservationService;
import cz.cvut.fel.rsp.server.service.RoomService;
import cz.cvut.fel.rsp.server.service.UnregUserService;
import java.util.List;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FN
 */
@RestController
@RequestMapping("/rest/roomService")
@PreAuthorize("permitAll()")
public class RoomServiceRest extends AbstractServices {
    
    @Autowired
    public RoomServiceRest(RoomService roomService, HotelService hotelService, RegUserService regUserService, ReservationService reservationService, UnregUserService unregUserService) {
        super(roomService, hotelService, regUserService, reservationService, unregUserService);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public void addRoom(@RequestBody Room room) {
        if(room != null) {
            roomService.addRoom(room);
        }
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRoom(@PathVariable Integer id, @RequestBody Room room) {
        Room oldRoom = roomService.findRoomById(id);
        if(oldRoom != null) {
            roomService.updateRoom(room);
        }
    }
    
    @DeleteMapping(value="/{id}")
    public void deleteRoom(@PathVariable Integer id) {
        Room toDelete = roomService.findRoomById(id);
        if(toDelete != null) {
            roomService.deleteRoom(toDelete);
        }
    }
    
}
