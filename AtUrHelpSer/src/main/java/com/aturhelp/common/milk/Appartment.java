package com.aturhelp.common.milk;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "appartment")
public class Appartment {

	private String appName;
	private String appSubject;
	private Integer status;
	
	@XmlElement(name ="appname")
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	@XmlElement(name ="appsubject")
	public String getAppSubject() {
		return appSubject;
	}
	public void setAppSubject(String appSubject) {
		this.appSubject = appSubject;
	}
	
	@XmlElement(name ="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
