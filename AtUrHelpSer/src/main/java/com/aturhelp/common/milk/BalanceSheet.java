package com.aturhelp.common.milk;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "balancesheet")
public class BalanceSheet {

	private String category;
	private float liters;
	private String route;
	
	
	@XmlElement(name ="route")
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	
	@XmlElement(name ="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@XmlElement(name ="liters")
	public float getLiters() {
		return liters;
	}
	public void setLiters(float liters) {
		this.liters = liters;
	}
	
	
}
