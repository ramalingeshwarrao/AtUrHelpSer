package com.aturhelp.common.milk;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "roommilk")
public class RoomMilk {

	private int roomId;
	private int milkId;
	private int quantity;
	private Integer status;
	
	
	@XmlElement(name ="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@XmlElement(name ="roomid")
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
	
	@XmlElement(name ="quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
