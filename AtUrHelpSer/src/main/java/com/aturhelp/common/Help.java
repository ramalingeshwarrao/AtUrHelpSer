package com.aturhelp.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "help")
public class Help {

	private String helpType;
	private String helpDes;
	private String deviceId;
	private String serviceId;
	private Long ticketNo;
	private Integer status;
	private String mobileNo;
	private String mail;
	private String area;
	
	@XmlElement(name ="htype")
	public String getHelpType() {
		return helpType;
	}
	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}
	
	@XmlElement(name ="hdes")
	public String getHelpDes() {
		return helpDes;
	}
	public void setHelpDes(String helpDes) {
		this.helpDes = helpDes;
	}
	
	@XmlElement(name ="hid")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@XmlElement(name ="hserid")
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	@XmlElement(name ="ticketno")
	public Long getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(Long ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	@XmlElement(name ="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@XmlElement(name ="mobileno")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@XmlElement(name ="email")
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@XmlElement(name ="area")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
}
