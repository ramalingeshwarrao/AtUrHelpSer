package com.aturhelp.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aturhelp.common.AdminInfo;
import com.aturhelp.common.BootStrapData;
import com.aturhelp.common.Help;
import com.aturhelp.common.Location;
import com.aturhelp.common.Services;
import com.aturhelp.common.UserInfo;

@Component
public interface HelpService {

	public UserInfo getUserData(String deviceId);
	public Location getLocation();
	public Services getServices();
	public boolean createUserInfo(UserInfo userInfo);
	public boolean updateUserInfo(UserInfo userInfo);
	public boolean createHelp(Help help) throws Exception;
	public boolean updateTicket(Help help);
	public AdminInfo getAdminInfo(String deviceId);
	public AdminInfo getAdminInfoByServiceId(String serviceId);
	public boolean createAdmin(AdminInfo admin);
	public void mail(String toEmail, String subject, String body);
	public boolean isCustomerExist(String deviceId);
	public AdminInfo getAdminInfoById(String adminId);
	public boolean updateAdminState(String value, String adminId, String deviceId);
	public boolean activateAdmin(String adminId, String deviceId);
	public List<Help> getLogData(String deviceId);
	public List<Help> getLogData(String providerName, String area, String name);
	public List<Help> getCustomerLogData(String deviceId);
	public String getDeviceIdFromTicketId(String ticketNo);
	public Boolean insertRegisterAdmin(AdminInfo adminInfo);
	public String getPassword(String user);
	public BootStrapData getBootStrapData();
}
