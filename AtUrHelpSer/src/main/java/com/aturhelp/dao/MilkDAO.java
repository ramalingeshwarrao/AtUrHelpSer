package com.aturhelp.dao;

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
public interface MilkDAO {
	
	//Insert Queries
	public boolean craetePackets(MilkPackets milkPackets);
	public boolean createAppartment(Appartment appartment);
	public boolean createFlatNo(FlatNo flatNo);
	public boolean createLocation(Location location);
	public boolean createRoomMilk(RoomMilk roomMilk);
	public boolean createRoute(Route route);
	
	
	//Select Queries
	public List<MilkPackets> getMilkPackets();
	public List<Appartment> getAppartments(String id);
	public List<Location> getLocations();
	public List<Route> getRoutes();
	public List<GetFlatsData> getFlatDetails();
	public List<FlatNo> getFlatNoDetails(String apartmentId);

}
