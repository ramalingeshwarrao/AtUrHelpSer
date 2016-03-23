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
import com.aturhelp.common.milk.Route;
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

}
