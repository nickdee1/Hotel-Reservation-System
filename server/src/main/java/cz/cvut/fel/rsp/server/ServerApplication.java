package cz.cvut.fel.rsp.server;

import cz.cvut.fel.rsp.server.Model.Hotel;
import cz.cvut.fel.rsp.server.dao.HotelDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ServerApplication.class, args);
		HotelDao hd = new HotelDao();
		List<Hotel> hotels = hd.findAll();
	}

}
