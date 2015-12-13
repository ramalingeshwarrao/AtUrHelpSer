package com.aturhelp.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.aturhelp.common.UserInfo;
import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.GetFlatsData;
import com.aturhelp.common.milk.ListRoomMilk;
import com.aturhelp.common.milk.Location;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.RoomMilk;
import com.aturhelp.common.milk.Route;
import com.aturhelp.constants.Constants;
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
	public RoomMilk createRoomMilk(@RequestBody ListRoomMilk listRoomMilk) {
		RoomMilk status = new RoomMilk();
		boolean insertStatus = false;
		try {
			List<RoomMilk> roomMilks = listRoomMilk.getMilks();
			for (RoomMilk rM : roomMilks) {
				insertStatus = milkService.createRoomMilk(rM);				
			}
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
	
	@GET
	@Path("milkdetails")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MilkPackets> getMilkData() {
		return milkService.getMilkPackets();
	}
	
	@GET
	@Path("flatdetails")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GetFlatsData> getFlatDetails() {
		return milkService.getFlatDetails();
	}
	
	@GET
	@Path("routedetails")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Route> getRouteDetails() {
		return milkService.getRoutes();
	}
	
	@GET
	@Path("locationdetails")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Location> getLocationDetails() {
		return milkService.getLocations();
	}
	
	@GET
	@Path("appartmentdetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Appartment> getAppartmentDetails(@QueryParam(Constants.ROUTE_ID) String id) {
		return milkService.getAppartments(id);
	}
	
	@GET
	@Path("getflatnodetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<FlatNo> getFlatNoDetails(@QueryParam(Constants.APP_ID) String app_id) {
		return milkService.getFlatNoDetails(app_id);
	}
}
