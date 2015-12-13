package com.aturhelp.common.milk;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "milk")
public class MilkPackets {
	
	private String subject;
	private String milkName;
	private float cost;
	private Integer status;
	private int id;
	
	@XmlElement(name ="subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@XmlElement(name ="milkname")
	public String getMilkName() {
		return milkName;
	}
	public void setMilkName(String milkName) {
		this.milkName = milkName;
	}
	
	@XmlElement(name ="cost")
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	@XmlElement(name ="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@XmlElement(name ="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
