package cz.cvut.fel.rsp.server.rest;


import cz.cvut.fel.rsp.server.Model.Reservation;
import cz.cvut.fel.rsp.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/rest/reservations")
public class ReservationRest extends AbstractServices{

    @Autowired
    public ReservationRest(RoomService roomService, HotelService hotelService, RegUserService regUserService, ReservationService reservationService, UnregUserService unregUserService) {
        super(roomService, hotelService, regUserService, reservationService, unregUserService);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getAllReservations() {
        return reservationService.getAllRes();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation getReservation(@PathVariable Integer id) {
        return reservationService.findResById(id);
    }
}
