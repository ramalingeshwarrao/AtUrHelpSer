package com.aturhelp.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "service")
public class Services {

	
	private String lName;
	private String service;
	
	@XmlElement(name ="lname")
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	@XmlElement(name ="service")
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
	
}
