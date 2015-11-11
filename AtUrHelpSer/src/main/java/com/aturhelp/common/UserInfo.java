package com.aturhelp.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userinfo")
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8687813799182071L;
	
	private String email;
	private String mobileNo;
	private String name;
	private String deviceId;
	private String requestId;
	private int status;
	
	public UserInfo() {
		this.email="";
		this.mobileNo="";
		this.name="";
		this.deviceId="";
		this.requestId="";
	} 
	
	public UserInfo(String email, String mobileNo, String name, String deviceId, String requestId) {
		this.email = email;
		this.deviceId = deviceId;
		this.mobileNo = mobileNo;
		this.name = name;
		this.requestId = requestId;
	}
	
	@XmlElement(name ="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement(name ="mobileno")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@XmlElement(name ="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name ="deviceid")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@XmlElement(name ="requestid")
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@XmlElement(name ="status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
