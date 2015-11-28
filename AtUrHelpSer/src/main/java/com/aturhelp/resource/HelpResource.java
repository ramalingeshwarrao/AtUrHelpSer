package com.aturhelp.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aturhelp.common.AdminInfo;
import com.aturhelp.common.BootStrapData;
import com.aturhelp.common.Help;
import com.aturhelp.common.Location;
import com.aturhelp.common.Services;
import com.aturhelp.common.UserInfo;
import com.aturhelp.constants.Constants;
import com.aturhelp.services.HelpService;

@Component
@Path("/hr")
public class HelpResource {

	@Autowired(required = true)
	HelpService helpService;

	@GET
	@Path("userinfo")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public UserInfo getUserData(@QueryParam(Constants.DEVICE_ID) String deviceId) {
		return helpService.getUserData(deviceId);
	}
	
	@GET
	@Path("customerexist")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public UserInfo isCustomerExist(@QueryParam(Constants.DEVICE_ID) String deviceId) {
		UserInfo info = new UserInfo();
		boolean status = helpService.isCustomerExist(deviceId);
		if (status) {
			info.setStatus(0);
		} else {
			info.setStatus(1);
		}
		return info;
	}

	@GET
	@Path("loc")
	@Produces({ MediaType.APPLICATION_JSON })
	public Location getLocData() {
		return helpService.getLocation();
	}

	@GET
	@Path("ser")
	@Produces({ MediaType.APPLICATION_JSON })
	public Services getServices() {
		return helpService.getServices();
	}

	@POST
	@Path("insert/user")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void createUserInfo(UserInfo userInfo) {
		helpService.createUserInfo(userInfo);
	}

	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public UserInfo updateUserInfo(UserInfo userInfo) {
		UserInfo UpdateUserInfo = null;
		boolean isUpdated = helpService.updateUserInfo(userInfo);
		if (isUpdated) {
			UpdateUserInfo = helpService.getUserData(userInfo.getDeviceId());
		}
		return UpdateUserInfo;
	}

	// Crate the customer request
	@POST
	@Path("help")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Help createHelp(Help help) {
		try {
			helpService.createHelp(help);
			help.setStatus(0);
		} catch (Exception e) {
			help.setStatus(1);
		}
		return help;
	}
	
	@GET
	@Path("admin/deviceid")
	@Produces({ MediaType.APPLICATION_JSON })
	public AdminInfo getAdminInfoByDeviceId(@QueryParam(Constants.DEVICE_ID) String deviceId) {
		return helpService.getAdminInfo(deviceId);
	}

	@GET
	@Path("admin/serviceid")
	@Produces({ MediaType.APPLICATION_JSON })
	public AdminInfo getAdminInfoByServiceId(@QueryParam(Constants.SERVICE_ID) String serviceId) {
		return helpService.getAdminInfoByServiceId(serviceId);
	}
	
	@POST
	@Path("insert/admin")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void createAdminInfo(AdminInfo adminInfo) {
		helpService.createAdmin(adminInfo);
	}
	
	
	@GET
	@Path("ata")//activate admin
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public AdminInfo activateAdmin(@QueryParam(Constants.ADMIN_ID) String adminId, @QueryParam(Constants.DEVICE_ID) String deviceId) {
		AdminInfo info = new AdminInfo();
		boolean status = helpService.activateAdmin(adminId, deviceId);
		if (status) {
			info.setStatus(0);
		} else {
			info.setStatus(1);
		}
		return info;
	}
	
	@GET
	@Path("logdata")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Help> getLogData(@QueryParam(Constants.DEVICE_ID) String deviceId) {
		return helpService.getLogData(deviceId);
	}
	
	@GET
	@Path("customerlogdata")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Help> getCustomerLogData(@QueryParam(Constants.DEVICE_ID) String deviceId) {
		return helpService.getCustomerLogData(deviceId);
	}
	
	@POST
	@Path("updateticketid")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Help updateTicket(Help help) {
		boolean isUpdated = helpService.updateTicket(help);
		if (isUpdated) {
			help.setStatus(0);
		} else {
			help.setStatus(1);
		}
		return help;
	}
	
	@POST
	@Path("insert/registeradmin")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public AdminInfo createRegisterAdmin(AdminInfo adminInfo) {
		boolean isInserted = helpService.insertRegisterAdmin(adminInfo);
		AdminInfo adminInfoStat = new AdminInfo();
		if (isInserted) {
			adminInfoStat.setStatus(0); //Succesfull
		} else {
			adminInfoStat.setStatus(1);
		}
		return adminInfoStat;
	}
	
	@GET
	@Path("bootstrapdata")
	@Produces({MediaType.APPLICATION_JSON})
	public BootStrapData getBootStrapData() {
		return helpService.getBootStrapData();
	}
	
	@GET
	@Path("logdataadmin")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Help> getLogData(@QueryParam(Constants.PROVIDER_NAME) String providerName, @QueryParam(Constants.PROVIDER_LOC) String providerLoc, @QueryParam(Constants.LOGGED_NAME) String name) {
		return helpService.getLogData(providerName, providerLoc, name);
	}
}
