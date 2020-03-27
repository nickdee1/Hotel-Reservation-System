package cz.cvut.fel.rsp.server.rest;

import cz.cvut.fel.rsp.server.Model.Reservation;
import cz.cvut.fel.rsp.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/rest/{user_id}")
@PreAuthorize("permitAll()")
public class ReservationRest extends AbstractServices {

    @Autowired
    public ReservationRest(RoomService roomService, HotelService hotelService, RegUserService regUserService, ReservationService reservationService, UnregUserService unregUserService) {
        super(roomService, hotelService, regUserService, reservationService, unregUserService);
    }

    @PostMapping (value = "/reservation/reg", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservationRegistered(@PathVariable Integer user_id, @RequestBody Reservation reservation) {
        reservationService.addReservationReg(reservation, user_id);
    }

    @PostMapping (value = "/reservation/unreg", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservationUnregistered(@PathVariable Integer user_id, @RequestBody Reservation reservation) {
        reservationService.addReservationUnreg(reservation, user_id);
    }
}
