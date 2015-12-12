package com.aturhelp.common.milk;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getflatdata")
public class GetFlatsData {

	private String appartmentSubject;
	private String appartmentName;
	private String routeSubject;
	private String routeName;
	private String roomId;
	private float cost;
	private Integer quantity;
	private String milkId;
	
	
	@XmlElement(name ="appsubject")
	public String getAppartmentSubject() {
		return appartmentSubject;
	}
	public void setAppartmentSubject(String appartmentSubject) {
		this.appartmentSubject = appartmentSubject;
	}
	
	@XmlElement(name ="appname")
	public String getAppartmentName() {
		return appartmentName;
	}
	public void setAppartmentName(String appartmentName) {
		this.appartmentName = appartmentName;
	}
	
	@XmlElement(name ="routesubject")
	public String getRouteSubject() {
		return routeSubject;
	}
	public void setRouteSubject(String routeSubject) {
		this.routeSubject = routeSubject;
	}
	
	@XmlElement(name ="routename")
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	@XmlElement(name ="roomid")
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	@XmlElement(name ="cost")
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	@XmlElement(name ="quantity")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@XmlElement(name ="milkid")
	public String getMilkId() {
		return milkId;
	}
	public void setMilkId(String milkId) {
		this.milkId = milkId;
	}
}
