package com.aturhelp.common.milk;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "location")
public class RoomMilk {

	private int roomId;
	private int milkId;
	private Integer status;
	
	
	@XmlElement(name ="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@XmlElement(name ="rooid")
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	@XmlElement(name ="milkid")
	public int getMilkId() {
		return milkId;
	}
	public void setMilkId(int milkId) {
		this.milkId = milkId;
	}
	
	
}
