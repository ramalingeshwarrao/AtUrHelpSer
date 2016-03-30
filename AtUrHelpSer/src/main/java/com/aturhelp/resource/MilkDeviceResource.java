package com.aturhelp.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aturhelp.common.Login;
import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.Route;
import com.aturhelp.constants.Constants;
import com.aturhelp.services.MilkService;

@Component
@Path("/device")
public class MilkDeviceResource {
	
final static Logger LOG = Logger.getLogger(MilkDeviceResource.class);
	
	@Autowired(required = true)
	MilkService milkService;
	
	//Login Validation
		@POST
		@Path("lv")
		@Consumes({ MediaType.APPLICATION_JSON })
		public Response loginValidation(Login login, @Context HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.setAttribute("user", login.getUserName());
			boolean res = milkService.validateLogin(login);
			if (res) {
				return Response.ok(0 + "").build();
			} else {
				return Response.ok(1 + "").build();
			}
		}
		
		@GET
		@Path("routedetails")
		@Produces({ MediaType.APPLICATION_JSON })
		public List<Route> getRouteDetails(@QueryParam("n") String userName) {
			return milkService.getRoutes(true, userName);
		}
		
		@GET
		@Path("appartmentdetails")
		@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
		@Produces({ MediaType.APPLICATION_JSON })
		public List<Appartment> getAppartmentDetails(@QueryParam(Constants.ROUTE_ID) String id, @QueryParam("n") String userName) {
			return milkService.getAppartments(id, true, userName);
		}
		
		@GET
		@Path("getflatnodetails")
		@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
		@Produces({ MediaType.APPLICATION_JSON })
		public List<FlatNo> getFlatNoDetails(@QueryParam(Constants.APP_ID) String app_id, @QueryParam("n") String userName) {
			return milkService.getFlatNoDetails(app_id, true, userName);
		}

}
