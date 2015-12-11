package com.aturhelp.services;

import com.aturhelp.common.Location;
import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.RoomMilk;
import com.aturhelp.common.milk.Route;

public interface MilkService {
	
	public boolean createPackets(MilkPackets milkPackets);
	public boolean createAppartment(Appartment appartment);
	public boolean createFlatNo(FlatNo flatNo);
	public boolean createLocation(Location location);
	public boolean createRoomMilk(RoomMilk roomMilk);
	public boolean createRoute(Route route);

}
