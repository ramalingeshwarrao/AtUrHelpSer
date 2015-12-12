package com.aturhelp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.Location;
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
		boolean insertStatus = false;
		try {
			insertStatus = milkService.createAppartment(appartment);
		} catch (Exception e) {
			LOG.error("Fail to create appartment record", e);
		}
		if (insertStatus) {
			status.setStatus(0);
			return status;
		} else {
			status.setStatus(1);
			return status;
		}
	}
	
	@POST
	@Path("insert/flatno")
	@Consumes({ MediaType.APPLICATION_JSON })
	public FlatNo createFlatNo(FlatNo flatNo) {
		FlatNo status = new FlatNo();
		boolean insertStatus = false;
		try {
			insertStatus = milkService.createFlatNo(flatNo);
		} catch (Exception e) {
			LOG.error("Fail to create flatno record", e);
		}
		if (insertStatus) {
			status.setStatus(0);
			return status;
		} else {
			status.setStatus(1);
			return status;
		}
	}
	
	@POST
	@Path("insert/location")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Location createLocation(Location location) {
		Location status = new Location();
		boolean insertStatus = false;
		try {
			insertStatus = milkService.createLocation(location);
		} catch (Exception e) {
			LOG.error("Fail to create milk location record", e);
		}
		if (insertStatus) {
			status.setStatus(0);
			return status;
		} else {
			status.setStatus(1);
			return status;
		}
	}
	
	@POST
	@Path("insert/milkpackets")
	@Consumes({ MediaType.APPLICATION_JSON })
	public MilkPackets createMilkPackets(MilkPackets milkPackets) {
		MilkPackets status = new MilkPackets();
		boolean insertStatus = false;
		try {
			insertStatus = milkService.createPackets(milkPackets);
		} catch (Exception e) {
			LOG.error("Fail to create milkpackets record", e);
		}
		if (insertStatus) {
			status.setStatus(0);
			return status;
		} else {
			status.setStatus(1);
			return status;
		}
	}
	
	@POST
	@Path("insert/roommilk")
	@Consumes({ MediaType.APPLICATION_JSON })
	public RoomMilk createRoomMilk(RoomMilk roomMilk) {
		RoomMilk status = new RoomMilk();
		boolean insertStatus = false;
		try {
			insertStatus = milkService.createRoomMilk(roomMilk);
		} catch (Exception e) {
			LOG.error("Fail to create Room milk record", e);
		}
		if (insertStatus) {
			status.setStatus(0);
			return status;
		} else {
			status.setStatus(1);
			return status;
		}
	}
	
	@POST
	@Path("insert/route")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Route createRoute(Route route) {
		Route status = new Route();
		boolean insertStatus = false;
		try {
			insertStatus = milkService.createRoute(route);
		} catch (Exception e) {
			LOG.error("Fail to create route record", e);
		}
		if (insertStatus) {
			status.setStatus(0);
			return status;
		} else {
			status.setStatus(1);
			return status;
		}
	}
}
