package com.aturhelp.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.GetFlatsData;
import com.aturhelp.common.milk.Location;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.RoomMilk;
import com.aturhelp.common.milk.Route;

@Component
public interface MilkService {
	
	//insert queries
	public boolean createPackets(MilkPackets milkPackets);
	public boolean createAppartment(Appartment appartment);
	public boolean createFlatNo(FlatNo flatNo);
	public boolean createLocation(Location location);
	public boolean createRoomMilk(RoomMilk roomMilk);
	public boolean createRoute(Route route);
	
	//select queries
	public List<MilkPackets> getMilkPackets();
	public List<Appartment> getAppartments();
	public List<Location> getLocations();
	public List<Route> getRoutes();
	public List<GetFlatsData> getFlatDetails();

}
