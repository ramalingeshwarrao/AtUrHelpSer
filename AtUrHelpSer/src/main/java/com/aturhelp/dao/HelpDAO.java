package com.aturhelp.dao;

import java.util.List;

import com.aturhelp.common.AdminInfo;
import com.aturhelp.common.Help;
import com.aturhelp.common.Location;
import com.aturhelp.common.Services;
import com.aturhelp.common.UserInfo;

public interface HelpDAO {
	public UserInfo getUserData(String deviceId);
	public Location getLocation();
	public Services getServices();
	public boolean createUserInfo(UserInfo userInfo);
	public boolean updateUserInfo(UserInfo userInfo);
	public boolean createHelp(Help help);
	public AdminInfo getAdminInfo(String deviceId);
	public AdminInfo getAdminInfoByServiceId(String serviceId);
	public boolean createAdmin(AdminInfo admin);
	public boolean updateTicket(Help help);
	public boolean isCustomerExist(String deviceId);
	public AdminInfo getAdminInfoById(String id);
	public boolean updateAdminState(String value, String adminId, String deviceid);
	public List<Help> getLogData(String deviceId);
	public List<Help> getCustomerLogData(String deviceId);
	public String getDeviceIdFromTicketId(String ticketNo);
}
