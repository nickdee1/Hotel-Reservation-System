package cz.cvut.fel.rsp.server.dao;

import cz.cvut.fel.rsp.server.Model.Hotel;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDao extends AbstractDao<Hotel> {

    public HotelDao() {
        super(Hotel.class);
    }
}
