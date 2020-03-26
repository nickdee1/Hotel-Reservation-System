package cz.cvut.fel.rsp.server.rest;


import cz.cvut.fel.rsp.server.Model.Reservation;
import cz.cvut.fel.rsp.server.Model.Room;
import cz.cvut.fel.rsp.server.Model.UnregisteredUser;
import cz.cvut.fel.rsp.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/unregUsers")
public class UnregisteredUserRest extends AbstractServices{

    @Autowired
    public UnregisteredUserRest(RoomService roomService, HotelService hotelService, RegUserService regUserService, ReservationService reservationService, UnregUserService unregUserService) {
        super(roomService, hotelService, regUserService, reservationService, unregUserService);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UnregisteredUser> getAllRooms() {
        return unregUserService.getAllUnregUsers();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UnregisteredUser getUser(@PathVariable Integer id) {
        return unregUserService.findUserById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUnregUser(@RequestBody UnregisteredUser unregisteredUser) {
        if (unregisteredUser != null) {
            unregUserService.createUser(unregisteredUser);
        }
    }

    @PostMapping(value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
        if (reservation != null)
            reservationService.createReservationRegistered(reservation);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUnregUser(@RequestBody UnregisteredUser unregisteredUser) {
        UnregisteredUser unregUser = unregUserService.findUserById(unregisteredUser.getId());
        if (unregUser != null) {
            unregUserService.updateUser(unregisteredUser);
        }
    }

    @DeleteMapping(value="/{id}")
    public void deleteUnregUser(@PathVariable Integer id) {
        UnregisteredUser userToDelete = unregUserService.findUserById(id);
        if (userToDelete != null) {
            unregUserService.deleteUser(userToDelete);
        }
    }

}
