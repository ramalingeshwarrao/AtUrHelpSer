package com.aturhelp.common.milk;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nomilk")
public class NoMilk {
	
	private int id;
	private int rid;
	private String formDate;
	private String toDate;
	private Boolean isUpdated;
	private Integer status;
	
	@XmlElement(name ="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name ="rid")
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	@XmlElement(name ="fromdate")
	public String getFormDate() {
		return formDate;
	}
	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}
	
	@XmlElement(name ="todate")
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	@XmlElement(name ="udpate")
	public Boolean getIsUpdated() {
		return isUpdated;
	}
	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	@XmlElement(name ="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
