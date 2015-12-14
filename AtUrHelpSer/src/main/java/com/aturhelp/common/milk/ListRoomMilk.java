package com.aturhelp.common.milk;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listroommilk")
public class ListRoomMilk {

	private List<RoomMilk> roommilks = new ArrayList<RoomMilk>();

	@XmlElement(name ="roommilks")
	public List<RoomMilk> getMilks() {
		return roommilks;
	}

	public void setMilks(List<RoomMilk> milks) {
		this.roommilks = milks;
	}
	
}
