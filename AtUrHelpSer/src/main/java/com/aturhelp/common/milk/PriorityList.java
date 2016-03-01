package com.aturhelp.common.milk;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pl")
public class PriorityList {
	
	private List<Priority> pList = new ArrayList<Priority>();

	@XmlElement(name ="plist")
	public List<Priority> getpList() {
		return pList;
	}

	public void setpList(List<Priority> pList) {
		this.pList = pList;
	}
}
