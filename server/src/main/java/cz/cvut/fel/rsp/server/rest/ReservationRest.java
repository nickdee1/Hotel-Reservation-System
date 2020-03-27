package cz.cvut.fel.rsp.server.rest;

import cz.cvut.fel.rsp.server.Model.Reservation;
import cz.cvut.fel.rsp.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/rest/{hotelid}/reservations")
@PreAuthorize("permitAll()")
public class ReservationRest extends AbstractServices {

    @Autowired
    public ReservationRest(RoomService roomService, HotelService hotelService, RegUserService regUserService, ReservationService reservationService, UnregUserService unregUserService) {
        super(roomService, hotelService, regUserService, reservationService, unregUserService);
    }

    @PostMapping(value = "/{user_id}/reg", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservationRegistered(@PathVariable Integer hotelid, @PathVariable Integer user_id, @RequestBody Reservation reservation) {
        reservationService.addReservationReg(reservation, user_id, hotelid);
    }

    @PostMapping(value = "/{user_id}/unreg", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReservationUnregistered(@PathVariable Integer hotelid, @PathVariable Integer user_id, @RequestBody Reservation reservation) {
        reservationService.addReservationUnreg(reservation, user_id, hotelid);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getReservationsByHotel(@PathVariable Integer hotelid) {
        return reservationService.getReservationsByHotel(hotelid);
    }
}
