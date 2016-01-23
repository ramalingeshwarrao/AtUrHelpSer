package com.aturhelp.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.BalanceSheet;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.GetFlatsData;
import com.aturhelp.common.milk.Location;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.NoMilk;
import com.aturhelp.common.milk.NoMilkCost;
import com.aturhelp.common.milk.RoomBill;
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
	public boolean craeteNoMilk(NoMilk noMilk);
	
	//select queries
	public List<MilkPackets> getMilkPackets();
	public List<Appartment> getAppartments(String id);
	public List<Location> getLocations();
	public List<Route> getRoutes();
	public List<GetFlatsData> getFlatDetails(String recordsPerPage, String fromRecord);
	public List<FlatNo> getFlatNoDetails(String appId);
	public Integer getMilkCount();
	public Boolean getMilkStatusByRid(int roomId);
	public List<GetFlatsData> getMilkDetailsByRouteId(int routeId, String date);
	public GetFlatsData getNoMilkDetails(int roomId, int appId);
	public List<GetFlatsData> getAllNoMilkDetails(int roomId, int appId);
	public boolean updateNoMilkToGetMilk(int roomId, String toDate);
	public boolean noMilkFirstCase(NoMilk nomilk);
	public boolean noMilkFirstCaseIfTODateNull(NoMilk nomilk);
	public boolean noMilkValidateFDGreaterGivenTD(NoMilk nomilk);
	public boolean noMilkSecondCase(NoMilk nomilk);
	public List<NoMilkCost> getMilkCostForAllFlatByApp(int appId);
	public boolean validateDateInRange(String date, int rid);
	public boolean validateRangeInRange(int rid, String fromDate, String toDate);
	
	/**
	 * Get data like how many days the user did not take milk
	 * @param noMilk
	 * @return
	 */
	public List<NoMilk> getNoMilkDetailsById(NoMilk noMilk);
	
	/**
	 * If no milk data is not there, than we need to validate any of the past month having null value for todate field.
	 * @param noMilk
	 * @return
	 */
	public NoMilk getNoMilkDetailsByIdForNull(NoMilk noMilk);
	
	public List<RoomBill> getFinalCostForRoomByAppId(int appId, String fromDate, String toDate) throws Exception;
	public List<BalanceSheet> getMilkSpendinLts();

}
