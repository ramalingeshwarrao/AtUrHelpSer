package com.aturhelp.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.aturhelp.common.UpdateMilk;
import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.BalanceSheet;
import com.aturhelp.common.milk.Category;
import com.aturhelp.common.milk.Comment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.GetFlatsData;
import com.aturhelp.common.milk.ListRoomMilk;
import com.aturhelp.common.milk.Location;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.NoMilk;
import com.aturhelp.common.milk.Priority;
import com.aturhelp.common.milk.PriorityList;
import com.aturhelp.common.milk.RoomBill;
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
				if (rM.getMilkId() != 0 && rM.getQuantity() != 0) {
					insertStatus = milkService.createRoomMilk(rM);					
				}
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
	
	@POST
	@Path("insert/category")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Category createCategory(Category cat) {
		Category status = new Category();
		boolean insertStatus = false;
		try {
			insertStatus = milkService.createCategory(cat);
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
	
	@POST
	@Path("insert/nomilk")
	@Consumes({ MediaType.APPLICATION_JSON })
	public NoMilk createNoMilk(NoMilk noMilk  ) {
		NoMilk status = new NoMilk();
		boolean insertStatus = false;
		try {
			insertStatus = milkService.craeteNoMilk(noMilk);
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
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GetFlatsData> getFlatDetails(@QueryParam(Constants.RECORDS_PAGE_SIZE) String recordsPerPage, @QueryParam(Constants.FROM_RECORD) String fromRecord) {
		return milkService.getFlatDetails(recordsPerPage, fromRecord);
	}
	
	@GET
	@Path("routedetails")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Route> getRouteDetails() {
		return milkService.getRoutes();
	}
	
	@GET
	@Path("catdetails")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Category> getCategoryDetails() {
		return milkService.getCategories();
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
	
	@GET
	@Path("milkcount")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public String getMilkDataCount() {
		//false means open tickets
		//true means closed tickets
		Integer count = milkService.getMilkCount();
		if (count != null) {
			return count+""; 
		} else {
			return "0";
		}
	}
	
	@GET
	@Path("dailymilkdetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GetFlatsData> getMilkDetails(@QueryParam(Constants.ROUTE_ID) int routeId,  @QueryParam(Constants.DATE) String date) {
		return milkService.getMilkDetailsByRouteId(routeId, date);
	}
	
	@GET
	@Path("getnomilkdetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public GetFlatsData getNoMilkDetails(@QueryParam(Constants.ROOM_ID) int roomId,  @QueryParam(Constants.APP_ID) int appId) {
		return milkService.getNoMilkDetails(roomId, appId);
	}
	
	@POST
	@Path("updatenomilktogetmilk")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public NoMilk updateTicket(NoMilk noMilk) {
		NoMilk sNoMilk = new NoMilk();
		boolean isUpdated = milkService.updateNoMilkToGetMilk(noMilk.getRid(), noMilk.getToDate());
		if (isUpdated) {
			sNoMilk.setStatus(0);
		} else {
			sNoMilk.setStatus(1);
		}
		return sNoMilk;
	}
	
	@GET
	@Path("getallnomilkdetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GetFlatsData> getAllNoMilkDetails(@QueryParam(Constants.ROOM_ID) int roomId,  @QueryParam(Constants.APP_ID) int appId) {
		return milkService.getAllNoMilkDetails(roomId, appId);
	}
	
	@GET
	@Path("getbill")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<RoomBill> getBill(@QueryParam(Constants.FROM_DATE) String fromDate, @QueryParam(Constants.TO_DATE) String toDate,  @QueryParam(Constants.APP_ID) int appId) {
		try {
			return milkService.getBillByAppId(appId, fromDate, toDate);
		} catch(Exception e) {
			LOG.error("Fail to generate bill", e);
		}
		return null;
	}
	
	@GET
	@Path("balancesheetdetails")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<BalanceSheet> getConsumedMilk(@QueryParam(Constants.DATE) String date) {
		return milkService.getMilkSpendinLts(date);
	}
	
	@GET
	@Path("balancesheetdetailsbyroute")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<BalanceSheet> getConsumedMilkByRoute(@QueryParam(Constants.DATE) String date) {
		return milkService.getMilkSpendinLtsByRoute(date);
	}
	
	@GET
	@Path("updatecomment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public FlatNo updateComment(@QueryParam("roomid") String roomId, @QueryParam("comment") String comment) {
		FlatNo fn = new FlatNo();
		try {
			if (milkService.setComment(roomId, comment)) {
				fn.setStatus(0);
			} else {
				fn.setStatus(1);
			}
		} catch (Exception e) {
			LOG.error("Fail to set comment", e);
			fn.setStatus(1);
		}
		return fn;
	}
	
	@GET
	@Path("comment")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Comment> getComment(@QueryParam("roomid") String roomId) {
		
		return milkService.getComment(roomId);
		
	}
	
	@GET
	@Path("milkdetailsbyid")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MilkPackets> getMilkDetailsByRoomId(@QueryParam("roomid") String roomId) {
		return milkService.getMilkByRoomId(roomId);
	}
	
	@GET
	@Path("milkquantity")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public Response getMilkQuantityByRoomIdMilkId(@QueryParam("roomid") String roomId, @QueryParam("milkid") String milkId) {
		
		int res =  milkService.getCountByMilkId(roomId, milkId);
		return Response.ok(res+"").build();
	}
	
	@GET
	@Path("updatealternative")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public Response updateAlternative(@QueryParam("roomid") String roomId, @QueryParam("milkid") String milkId, @QueryParam("altercount") int alterCount) {
		
		boolean res = milkService.updateIsAlternativeToTrue(roomId, milkId, alterCount);
		if (res) {
			return Response.ok(0+"").build();
		} else {
			return Response.ok(1+"").build();
		}
	}
	
	@GET
	@Path("inactive")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public Response flatInactive(@QueryParam("roomid") String roomId, @QueryParam("cd") String cancelDate) {
		
		boolean res = milkService.inActiveFalt(roomId, cancelDate);
		if (res) {
			return Response.ok(0+"").build();
		} else {
			return Response.ok(1+"").build();
		}
	}
	
	@POST
	@Path("editmilk")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response editMilk(UpdateMilk um) {
		boolean res = milkService.updateMilkData(um);
		if (res) {
			return Response.ok(0 + "").build();
		} else {
			return Response.ok(1 + "").build();
		}
	}
	
	@GET
	@Path("dmt")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public Response deleteMilkTimerData(@QueryParam("roomid") Integer rid, @QueryParam("milkid") Integer mid, @QueryParam("sd") String supplyDate) {
		
		boolean res = milkService.deleteMilkTimerData(rid, mid, supplyDate);
		if (res) {
			return Response.ok(0+"").build();
		} else {
			return Response.ok(1+"").build();
		}
	}
	
	@GET
	@Path("priority")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Priority> getPriorityData(@QueryParam("routeid") Integer routeId) {
		return milkService.getPriorityListByRouteId(routeId);
	}
	
	@POST
	@Path("updatepriority")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updatePriority(@RequestBody PriorityList pl) {
		try {
			 if (pl != null && pl.getpList().size() > 0) {
				 milkService.updatePriority(pl.getpList());	 
			 } else {
				 return Response.ok(1 + "").build();
			 }
		} catch (Exception e) {
			LOG.error("Fail to update priority",e);
			return Response.ok(1 + "").build();
		}
		return Response.ok(0 + "").build();
	}
}


