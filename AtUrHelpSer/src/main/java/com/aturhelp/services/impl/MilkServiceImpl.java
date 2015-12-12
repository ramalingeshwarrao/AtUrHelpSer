package com.aturhelp.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.Location;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.RoomMilk;
import com.aturhelp.common.milk.Route;
import com.aturhelp.dao.MilkDAO;
import com.aturhelp.services.MilkService;

@Component
public class MilkServiceImpl implements MilkService{

final static Logger LOG = Logger.getLogger(MilkServiceImpl.class);
	
	@Autowired(required=true)
	MilkDAO milkDAO;
	
	@Override
	public boolean createPackets(MilkPackets milkPackets) {
		return milkDAO.craetePackets(milkPackets);
	}

	@Override
	public boolean createAppartment(Appartment appartment) {
		return milkDAO.createAppartment(appartment);
	}

	@Override
	public boolean createFlatNo(FlatNo flatNo) {
		return milkDAO.createFlatNo(flatNo);
	}

	@Override
	public boolean createLocation(Location location) {
		return milkDAO.createLocation(location);
	}

	@Override
	public boolean createRoomMilk(RoomMilk roomMilk) {
		return milkDAO.createRoomMilk(roomMilk);
	}

	@Override
	public boolean createRoute(Route route) {
		return milkDAO.createRoute(route);
	}

}
