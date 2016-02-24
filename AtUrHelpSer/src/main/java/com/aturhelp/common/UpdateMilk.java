package com.aturhelp.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "upatemilk")
public class UpdateMilk {

	private Integer milkRoomMilkId;
	private Integer milkRoomRoomId;
	private Integer milkRoomquantity;
	private Integer milkTimerMilkId;
	private Integer milkTimerRoomId;
	private Integer milkTimerquantity;
	private Integer newMilkId;
	private Integer newQuantity;
	private String supplyDate;
	
	@XmlElement(name ="supplydate")
	public String getSupplyDate() {
		return supplyDate;
	}
	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}
	@XmlElement(name ="newmilkid")
	public Integer getNewMilkId() {
		return newMilkId;
	}
	public void setNewMilkId(Integer newMilkId) {
		this.newMilkId = newMilkId;
	}
	
	@XmlElement(name ="newquantity")
	public Integer getNewQuantity() {
		return newQuantity;
	}
	public void setNewQuantity(Integer newQuantity) {
		this.newQuantity = newQuantity;
	}
	@XmlElement(name ="mrmilkid")
	public Integer getMilkRoomMilkId() {
		return milkRoomMilkId;
	}
	public void setMilkRoomMilkId(Integer milkRoomMilkId) {
		this.milkRoomMilkId = milkRoomMilkId;
	}
	
	@XmlElement(name ="mrroomid")
	public Integer getMilkRoomRoomId() {
		return milkRoomRoomId;
	}
	public void setMilkRoomRoomId(Integer milkRoomRoomId) {
		this.milkRoomRoomId = milkRoomRoomId;
	}
	
	@XmlElement(name ="mrqty")
	public Integer getMilkRoomquantity() {
		return milkRoomquantity;
	}
	public void setMilkRoomquantity(Integer milkRoomquantity) {
		this.milkRoomquantity = milkRoomquantity;
	}
	
	@XmlElement(name ="mtmilkid")
	public Integer getMilkTimerMilkId() {
		return milkTimerMilkId;
	}
	public void setMilkTimerMilkId(Integer milkTimerMilkId) {
		this.milkTimerMilkId = milkTimerMilkId;
	}
	
	@XmlElement(name ="mtroomid")
	public Integer getMilkTimerRoomId() {
		return milkTimerRoomId;
	}
	public void setMilkTimerRoomId(Integer milkTimerRoomId) {
		this.milkTimerRoomId = milkTimerRoomId;
	}
	
	@XmlElement(name ="mtqty")
	public Integer getMilkTimerquantity() {
		return milkTimerquantity;
	}
	public void setMilkTimerquantity(Integer milkTimerquantity) {
		this.milkTimerquantity = milkTimerquantity;
	}
	
	
}
