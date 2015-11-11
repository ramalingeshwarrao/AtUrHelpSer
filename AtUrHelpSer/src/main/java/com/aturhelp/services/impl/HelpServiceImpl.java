package com.aturhelp.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aturhelp.common.AdminInfo;
import com.aturhelp.common.Help;
import com.aturhelp.common.Location;
import com.aturhelp.common.Mail;
import com.aturhelp.common.Random;
import com.aturhelp.common.Services;
import com.aturhelp.common.UserInfo;
import com.aturhelp.constants.Constants;
import com.aturhelp.dao.HelpDAO;
import com.aturhelp.gcm.GCMBraoadCast;
import com.aturhelp.handler.InitiateGCMEvent;
import com.aturhelp.handler.AbstractEventObject.HandlerMode;
import com.aturhelp.handler.InitiateMailEvent;
import com.aturhelp.services.HelpService;
import com.aturhelp.utils.AtUrHelpUtils;

@Component
public class HelpServiceImpl implements HelpService {

	final static Logger LOG = Logger.getLogger(HelpServiceImpl.class);
	
	@Autowired(required=true)
	HelpDAO helpDAO;
	
	@Override
	public UserInfo getUserData(String deviceId) {
		return helpDAO.getUserData(deviceId);
	}

	@Override
	public Location getLocation() {
		return helpDAO.getLocation();
	}

	@Override
	public Services getServices() {
		return helpDAO.getServices();
	}

	@Override
	public boolean createUserInfo(UserInfo userInfo) {
		//Before inserting a record we need to check, whether record exist for that user or not.
		UserInfo uInfo = getUserData(userInfo.getDeviceId());
		if (uInfo == null) {
			return helpDAO.createUserInfo(userInfo);	
		} else {
			return helpDAO.updateUserInfo(userInfo);
		}
		
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		return helpDAO.updateUserInfo(userInfo);
	}

	@Override
	public boolean createHelp(Help help) throws Exception{
		GCMBraoadCast braodCast = null;
		Long ticketNo = Random.getInstance().nextId();
		help.setTicketNo(ticketNo);
		boolean isCreated = helpDAO.createHelp(help);
		AdminInfo adminInfo = null;
		UserInfo userInfo = null;
		if (isCreated) {
			adminInfo = getAdminInfoByServiceId(help.getServiceId());
			userInfo = getUserData(help.getDeviceId());
			String body = AtUrHelpUtils.generateMailBody(help, adminInfo, userInfo);
			//Send GCM message to admin
			List<String> requestIdList = new ArrayList<String>(1);
			if (adminInfo != null) {
				
				//Sample gcm message
				try {
					InitiateGCMEvent event = new InitiateGCMEvent();
					event.setRequestId(adminInfo.getRequestId());
					event.setNotificationType(Constants.ADMIN_NOTIFICATION);
					event.fireEvent(HandlerMode.SYNC);
					
//					requestIdList.add(adminInfo.getRequestId());
//					braodCast = new GCMBraoadCast(Constants.SERVER_KEY, requestIdList, Constants.ADMIN_NOTIFICATION, Constants.ATURHELP);
//					LOG.debug("Before sending gcm message");
//					braodCast.braodCastMessage();	
//					LOG.debug("After sending gcm message");
				} catch (Exception e) {
					LOG.error("Fail Initiate gcm listener", e);
				}
				
				//Send Mail
				//mail(adminInfo.getEmail(), help.getHelpType()+" Service Id : "+help.getServiceId(), body);
				try {
					InitiateMailEvent event = new InitiateMailEvent();
					event.setToMailAddress(adminInfo.getEmail());
					event.setFromMailAddress(Constants.EMAIL);
					event.setMailSubject( help.getHelpType()+" Service Id : "+help.getServiceId());
					event.setMailDescription(body);
					event.fireEvent(HandlerMode.SYNC);
				} catch (Exception e) {
					LOG.error("Fail to send mail", e);
				}
				return true;				
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public AdminInfo getAdminInfo(String deviceId) {
		return helpDAO.getAdminInfo(deviceId);
	}

	@Override
	public AdminInfo getAdminInfoByServiceId(String serviceId) {
		return helpDAO.getAdminInfoByServiceId(serviceId);
	}

	@Override
	public boolean updateTicket(Help help) {
		Boolean updateStatus = helpDAO.updateTicket(help);
		UserInfo info = null;
		String mailBody = null;
		GCMBraoadCast braodCast = null;
		if (updateStatus) {
			String deviceId = getDeviceIdFromTicketId(help.getTicketNo()+"");
			info = getUserData(deviceId);
			if (info != null) {
				mailBody = AtUrHelpUtils
						.generateMailBodyForCustomer(help, info);
				// Send GCM message to admin
				List<String> requestIdList = new ArrayList<String>(1);
				// Send GCM Message
				try {
					requestIdList.add(info.getRequestId());
					
					InitiateGCMEvent event = new InitiateGCMEvent();
					event.setRequestId(info.getRequestId());
					event.setNotificationType(Constants.CUSTOMER_NOTIFICATION);
					event.fireEvent(HandlerMode.SYNC);
					
//					braodCast = new GCMBraoadCast(Constants.SERVER_KEY,
//							requestIdList, Constants.CUSTOMER_NOTIFICATION,
//							Constants.ATURHELP);
//					LOG.debug("Before sending gcm message");
//					braodCast.braodCastMessage();
//					LOG.debug("After sending gcm message");
				} catch (Exception e) {
					LOG.error("Fail to send GCM message", e);
				}
				// Send Mail
				//mail(info.getEmail(), help.getHelpType() , mailBody);
				try {
					InitiateMailEvent event = new InitiateMailEvent();
					event.setToMailAddress(info.getEmail());
					event.setFromMailAddress(Constants.EMAIL);
					event.setMailSubject( help.getHelpType());
					event.setMailDescription(mailBody);
					event.fireEvent(HandlerMode.SYNC);
				} catch (Exception e) {
					LOG.error("Fail to send mail", e);
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean createAdmin(AdminInfo admin) {
		return helpDAO.createAdmin(admin);
	}

	@Override
	public void mail(String toEmail, String subject, String body) {
		try {
			Mail mail = new Mail(toEmail, subject, body);
			Thread mailThread = new Thread(mail);
			mailThread.start();	
		} catch (Exception e) {
			LOG.error("Fail to send mail ", e);
		}
		
		
	}

	@Override
	public boolean isCustomerExist(String deviceId) {
		return helpDAO.isCustomerExist(deviceId);
	}

	@Override
	public AdminInfo getAdminInfoById(String adminId) {
		return helpDAO.getAdminInfoById(adminId);
	}

	@Override
	public boolean updateAdminState(String value, String adminId, String deviceId) {
		return helpDAO.updateAdminState(value, adminId, deviceId);
	}

	@Override
	public boolean activateAdmin(String adminId, String deviceId) {
		// Validate whether admin  exist or not
		AdminInfo adminInfo = getAdminInfoById(adminId);
		
		if (adminInfo != null) {
			return updateAdminState(Constants.ADMIN_ACTIVE, adminId, deviceId);
		} else {
			return false;
		}
// For feature purpose i commented
//		if (adminInfo != null && adminInfo.getIsActive() != null
//				&& adminInfo.getIsActive().equals(Constants.ADMIN_ACTIVE)) {
//			return true;
//		} else if (adminInfo != null) {
//			return updateAdminState(Constants.ADMIN_ACTIVE, adminId);
//		} else {
//			return false;
//		}
	}

	@Override
	public List<Help> getLogData(String deviceId) {
		return helpDAO.getLogData(deviceId);
	}

	@Override
	public List<Help> getCustomerLogData(String deviceId) {
		return helpDAO.getCustomerLogData(deviceId);
	}

	@Override
	public String getDeviceIdFromTicketId(String ticketNo) {
		return helpDAO.getDeviceIdFromTicketId(ticketNo);
	}

}
