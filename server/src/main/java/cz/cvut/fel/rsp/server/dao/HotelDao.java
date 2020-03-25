package cz.cvut.fel.rsp.server.dao;

import cz.cvut.fel.rsp.server.Model.Hotel;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class HotelDao extends AbstractDao<Hotel> {

    public HotelDao() {
        super(Hotel.class);
    }
    
    
}
