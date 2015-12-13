package com.aturhelp.common.milk;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listroommilk")
public class ListRoomMilk {

	private List<RoomMilk> milks;

	@XmlElement(name ="milks")
	public List<RoomMilk> getMilks() {
		return milks;
	}

	public void setMilks(List<RoomMilk> milks) {
		this.milks = milks;
	}
	
}
