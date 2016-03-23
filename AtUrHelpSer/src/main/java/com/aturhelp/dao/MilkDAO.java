package com.aturhelp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.aturhelp.common.Login;
import com.aturhelp.common.UpdateMilk;
import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.BalanceSheet;
import com.aturhelp.common.milk.Category;
import com.aturhelp.common.milk.Comment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.GetFlatsData;
import com.aturhelp.common.milk.Location;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.NoMilk;
import com.aturhelp.common.milk.NoMilkCost;
import com.aturhelp.common.milk.Priority;
import com.aturhelp.common.milk.RoomBill;
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
	public boolean createCategory(Category cat);
	
	
	//Select Queries
	public List<MilkPackets> getMilkPackets();
	public List<Appartment> getAppartments(String id);
	public List<Location> getLocations();
	public List<Route> getRoutes(Boolean isSelReq, Boolean isDeviceReq, String providerName);
	public List<Category> getCategories();
	public List<GetFlatsData> getFlatDetails(String recordsPerPage, String fromRecord);
	public List<FlatNo> getFlatNoDetails(String apartmentId);
	public Integer getMilkCount();
	public boolean craeteNoMilk(NoMilk noMilk);
	public Boolean getMilkStatusByRid(int roomId);
	public List<GetFlatsData> getMilkDetailsByRouteId(int routeId, String date);
	public List<GetFlatsData> getMilkDetails(String date, String providerName);
	public GetFlatsData getNoMilkDetails(int roomId, int appId);
	public List<GetFlatsData> getAllNoMilkDetails(int roomId, int appId);
	public boolean updateNoMilkToGetMilk(int roomId, String toDate);
	public boolean noMilkFirstCase(NoMilk nomilk);
	public boolean noMilkFirstCaseIfTODateNull(NoMilk nomilk);
	public boolean noMilkSecondCase(NoMilk nomilk);
	public boolean noMilkValidateFDGreaterGivenTD(NoMilk nomilk);
	public List<NoMilkCost> getMilkCostForAllFlatByApp(int appId);
	public boolean validateDateInRange(String date, int rid);
	public boolean validateRangeInRange(int rid, String fromDate, String toDate);
	public boolean setComment(String roomId, String comment);
	public List<Comment> getComment(String roomId);
	
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
	
	public List<BalanceSheet> getMilkSpendinLts(String strDate);
	
	public List<BalanceSheet> getMilkSpendinLtsByRoute(String strDate);
	
	public List<MilkPackets> getMilkByRoomId(String roomId);
	
	public int getCountByMilkId(String roomId, String milkId);
	
	public boolean updateIsAlternativeToTrue(String roomId, String milkId, int alterCount) ;
	
	public boolean inActiveFalt(String roomId, String cancelDate);
	
	public void updateMilkTimerData(List<GetFlatsData> flatsData);
	
	public List<String> getProviders();
	
	public boolean updateMilkData(UpdateMilk um);
	
	public boolean deleteMilkTimerData(Integer rid, Integer mid, String supplyDate);
	
	public List<RoomBill> getBillByAppId(int appId, String fromDate, String toDate);
	
	public List<Priority> getPriorityListByRouteId(int routeId);
	
	public void updatePriority(List<Priority> priority) throws Exception;
	
	public void updateTestMilkTimerData(final List<GetFlatsData> flatsData, String date) ;
	
	public boolean routeInactive(String routeId);
	
	public boolean validateLogin(Login login);

}
