package com.aturhelp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aturhelp.common.Location;
import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.RoomMilk;
import com.aturhelp.common.milk.Route;
import com.aturhelp.services.MilkService;

@Component
@Path("/milk")
public class MilkResource {

	final static Logger LOG = Logger.getLogger(MilkResource.class);
	
	@Autowired(required = true)
	MilkService milkService;
	
	@POST
	@Path("insert/appartment")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Appartment createAppartment(Appartment appartment) {
		Appartment status = new Appartment();
		try {
			milkService.createAppartment(appartment);
			status.setStatus(0);
		} catch (Exception e) {
			status.setStatus(1);
		}
		return status;
	}
	
	@POST
	@Path("insert/flatno")
	@Consumes({ MediaType.APPLICATION_JSON })
	public FlatNo createFlatNo(FlatNo flatNo) {
		FlatNo status = new FlatNo();
		try {
			milkService.createFlatNo(flatNo);
			status.setStatus(0);
		} catch (Exception e) {
			status.setStatus(1);
		}
		return status;
	}
	
	@POST
	@Path("insert/location")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Location createLocation(Location location) {
		Location status = new Location();
		try {
			milkService.createLocation(location);
			status.setStatus(0);
		} catch (Exception e) {
			status.setStatus(1);
		}
		return status;
	}
	
	@POST
	@Path("insert/milkpackets")
	@Consumes({ MediaType.APPLICATION_JSON })
	public MilkPackets createMilkPackets(MilkPackets milkPackets) {
		MilkPackets status = new MilkPackets();
		try {
			milkService.createPackets(milkPackets);
			status.setStatus(0);
		} catch (Exception e) {
			status.setStatus(1);
		}
		return status;
	}
	
	@POST
	@Path("insert/roommilk")
	@Consumes({ MediaType.APPLICATION_JSON })
	public MilkPackets createRoomMilk(RoomMilk roomMilk) {
		MilkPackets status = new MilkPackets();
		try {
			milkService.createRoomMilk(roomMilk);
			status.setStatus(0);
		} catch (Exception e) {
			status.setStatus(1);
		}
		return status;
	}
	
	@POST
	@Path("insert/route")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Route createRoute(Route route) {
		Route status = new Route();
		try {
			milkService.createRoute(route);
			status.setStatus(0);
		} catch (Exception e) {
			status.setStatus(1);
		}
		return status;
	}
}
