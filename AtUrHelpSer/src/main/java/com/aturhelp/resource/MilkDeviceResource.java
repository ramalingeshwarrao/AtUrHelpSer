package com.aturhelp.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aturhelp.common.Login;
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
		public Response loginValidation(Login login) {
			System.out.println("Inside the login method");
			boolean res = milkService.validateLogin(login);
			if (res) {
				return Response.ok(0 + "").build();
			} else {
				return Response.ok(1 + "").build();
			}
		}

}
