package com.aturhelp.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.aturhelp.dao.MilkDAO;
import com.aturhelp.services.MilkService;
import com.aturhelp.utils.AtUrHelpUtils;

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

	@Override
	public List<MilkPackets> getMilkPackets() {
		return milkDAO.getMilkPackets();
	}

	@Override
	public List<Appartment> getAppartments(String id) {
		return milkDAO.getAppartments(id);
	}

	@Override
	public List<Location> getLocations() {
		return milkDAO.getLocations();
	}

	@Override
	public List<Route> getRoutes() {
		return milkDAO.getRoutes(true);
	}

	@Override
	public List<GetFlatsData> getFlatDetails(String recordsPerPage, String fromRecord) {
		return milkDAO.getFlatDetails(recordsPerPage, fromRecord);
	}

	@Override
	public List<FlatNo> getFlatNoDetails(String appId) {
		return milkDAO.getFlatNoDetails(appId);
	}

	@Override
	public Integer getMilkCount() {
		return milkDAO.getMilkCount();
	}

	@Override
	public boolean craeteNoMilk(NoMilk noMilk) {
		
		
		//Need to validate whether record already exist or not
		if (noMilk != null && StringUtils.isBlank(noMilk.getToDate())) {
			
			boolean isFirstCaseIfToDateNull = noMilkFirstCaseIfTODateNull(noMilk);
			if (isFirstCaseIfToDateNull) {
				return false;
			}
			
			//First Case : Fromdate is already available and todate is not given
			boolean isFirstCase = noMilkFirstCase(noMilk);
			if (isFirstCase) {
				return false;
			}
		}
		
		boolean isFromDateValid = validateDateInRange(noMilk.getFormDate(), noMilk.getRid());
		if (isFromDateValid) {
			return false;
		}
		
		if (noMilk != null && !StringUtils.isBlank(noMilk.getToDate())) {
			boolean isToDateValid = validateDateInRange(noMilk.getToDate(), noMilk.getRid());
			if (isToDateValid) {
				return false;
			}
			
			//Validate range in range
			boolean isRangeValidate = validateRangeInRange(noMilk.getRid(), noMilk.getFormDate(), noMilk.getToDate());
			if (isRangeValidate) {
				return false;
			}
		}
		
		
		
//		//If toDate is null, we need to validate whether date before from date is exist or not
//		//Ex : Record : 10-01-2016 to NULL, if user tries to insert 09-01-2016 to NULL than we need to thorw error like 10-01-2016 record exist
//		if (noMilk != null && StringUtils.isBlank(noMilk.getToDate())) {
//			
//			boolean isFDateGtThanGiveToDate = noMilkValidateFDGreaterGivenTD(noMilk);
//			if (isFDateGtThanGiveToDate) {
//				return false;
//			}
//			boolean isFirstCaseIfToDateNull = noMilkFirstCaseIfTODateNull(noMilk);
//			if (isFirstCaseIfToDateNull) {
//				return false;
//			}
//			
//		}
//		
//		//Need to validate whether record already exist or not
//		//First Case : Fromdate is already available and todate is not given
//		//Second Case : FromDate & todate both are available 
//		//Validate first case
//		boolean isFirstCase = noMilkFirstCase(noMilk);
//		if (isFirstCase) {
//			return false;
//		}
//		//Need to validate second case
//		boolean isSecondCase = noMilkSecondCase(noMilk);
//		if (isSecondCase) {
//			return false;
//		}
		//Boolean isRecordExist = getMilkStatusByRid(noMilk.getRid()) ;
		//if (isRecordExist == null || isRecordExist) {
			return milkDAO.craeteNoMilk(noMilk);	
		//} else {
			//return false;
		//}
		
	}

	@Override
	public Boolean getMilkStatusByRid(int roomId) {
		return milkDAO.getMilkStatusByRid(roomId);
	}

	@Override
	public List<GetFlatsData> getMilkDetailsByRouteId(int routeId, String date) {
		return milkDAO.getMilkDetailsByRouteId(routeId, date);
	}

	@Override
	public GetFlatsData getNoMilkDetails(int roomId, int appId) {
		return milkDAO.getNoMilkDetails(roomId, appId);
	}

	@Override
	public boolean updateNoMilkToGetMilk(int roomId, String toDate) {
		return milkDAO.updateNoMilkToGetMilk(roomId, toDate);
	}

	@Override
	public boolean noMilkFirstCase(NoMilk nomilk) {
		return milkDAO.noMilkFirstCase(nomilk);
	}
	
	@Override
	public boolean noMilkFirstCaseIfTODateNull(NoMilk nomilk) {
		return milkDAO.noMilkFirstCaseIfTODateNull(nomilk);
	}

	@Override
	public boolean noMilkSecondCase(NoMilk nomilk) {
		return milkDAO.noMilkSecondCase(nomilk);
	}

	@Override
	public List<GetFlatsData> getAllNoMilkDetails(int roomId, int appId) {
		return milkDAO.getAllNoMilkDetails(roomId, appId);
	}

	@Override
	public List<NoMilkCost> getMilkCostForAllFlatByApp(int appId) {
		return milkDAO.getMilkCostForAllFlatByApp(appId);
	}

	@Override
	public List<NoMilk> getNoMilkDetailsById(NoMilk noMilk) {
		return milkDAO.getNoMilkDetailsById(noMilk);
	}

	@Override
	public NoMilk getNoMilkDetailsByIdForNull(NoMilk noMilk) {
		return milkDAO.getNoMilkDetailsByIdForNull(noMilk);
	}
	
	
	public List<RoomBill> getFinalCostForRoomByAppId(int appId, String fromDate, String toDate) throws Exception{
		// Get No of Days
		int totalDays = AtUrHelpUtils.getNoOfDaysInRange(fromDate, toDate, null);
		// Get All data
		List<NoMilkCost> milkCostDetails = getMilkCostForAllFlatByApp(appId);
		List<RoomBill> roomBillList = new ArrayList<RoomBill>();
		if (milkCostDetails != null) {
		for (NoMilkCost noMilkCost : milkCostDetails) {
			RoomBill rB = new RoomBill();
			Integer id = noMilkCost.getId();
			Integer cost = noMilkCost.getCost();
			Integer totalCost = totalDays * cost;
			String roomNo = noMilkCost.getRoomId();
			Integer quantity = noMilkCost.getQuantity();
			rB.setRoomId(roomNo);
			rB.setQuantity(quantity);
			NoMilk noMilk = new NoMilk();
			noMilk.setFormDate(fromDate);
			noMilk.setToDate(toDate);
			noMilk.setRid(id);
			// Validate any time milk was not taken
			List<NoMilk> getNoMilkDetails = getNoMilkDetailsById(noMilk);
			// If getNomilk details are empty it means that milk was taken or no
			// milk was taken
			if (getNoMilkDetails != null && getNoMilkDetails.size() > 0) {
				for (NoMilk noMil : getNoMilkDetails) {
					String fDate = null;
					if (StringUtils.isNotBlank(noMil.getFormDate())) 
						fDate =	AtUrHelpUtils.convertDateFormat(noMil.getFormDate(), AtUrHelpUtils.pattern_1, AtUrHelpUtils.pattern);
					
					String tDate = null; 
					if (StringUtils.isNotBlank(noMil.getToDate()))
					   tDate = AtUrHelpUtils.convertDateFormat(noMil.getToDate(), AtUrHelpUtils.pattern_1, AtUrHelpUtils.pattern);
					
					// if tDate is not null it means that nomilk is in that
					// range
					if (StringUtils.isBlank(tDate)) {
						Integer noMilkRange = AtUrHelpUtils.getNoOfDaysInRange(
								fDate, toDate, null);
						totalCost = totalCost - noMilkRange * cost;
					} else {
						String fCDate = fDate;
						String tCDate = tDate;
						boolean isTDateValid = AtUrHelpUtils.compareDates(tDate, toDate, AtUrHelpUtils.pattern);
						if (!isTDateValid) {
							tCDate = toDate;
						}
						Integer noMilkRange = AtUrHelpUtils.getNoOfDaysInRange(
								fCDate, tCDate, null);
						totalCost = totalCost - noMilkRange * cost;
					}
				}
			} else {
				NoMilk getNullMilk = getNoMilkDetailsByIdForNull(noMilk);
				if (getNullMilk != null) {
					// It means record exist set totalcost = 0
					totalCost = 0;
				}
			}
			rB.setCost(totalCost);
			roomBillList.add(rB);
		}
		}
		return roomBillList;
	}

	@Override
	public boolean noMilkValidateFDGreaterGivenTD(NoMilk nomilk) {
		return milkDAO.noMilkValidateFDGreaterGivenTD(nomilk);
	}

	@Override
	public boolean validateDateInRange(String date, int rid) {
		return milkDAO.validateDateInRange(date, rid);
	}

	@Override
	public boolean validateRangeInRange(int rid, String fromDate, String toDate) {
		return milkDAO.validateRangeInRange(rid, fromDate, toDate);
	}

	@Override
	public List<BalanceSheet> getMilkSpendinLts(String strDate) {
		return milkDAO.getMilkSpendinLts(strDate);
	}

	@Override
	public List<BalanceSheet> getMilkSpendinLtsByRoute(String strDate) {
		List<BalanceSheet> balanceSheet = milkDAO.getMilkSpendinLtsByRoute(strDate);
		return balanceSheet;
	}

	@Override
	public boolean createCategory(Category cat) {
		return milkDAO.createCategory(cat);
	}

	@Override
	public List<Category> getCategories() {
		return milkDAO.getCategories();
	}

	@Override
	public boolean setComment(String roomId, String comment) {
		return milkDAO.setComment(roomId, comment);
	}

	@Override
	public List<Comment> getComment(String roomId) {
		return milkDAO.getComment(roomId);
	}

	@Override
	public List<MilkPackets> getMilkByRoomId(String roomId) {
		return milkDAO.getMilkByRoomId(roomId);
	}

	@Override
	public int getCountByMilkId(String roomId, String milkId) {
		return milkDAO.getCountByMilkId(roomId, milkId);
	}

	@Override
	public boolean updateIsAlternativeToTrue(String roomId, String milkId, int alterCount) {
		return milkDAO.updateIsAlternativeToTrue(roomId, milkId, alterCount);
	}

	@Override
	public boolean inActiveFalt(String roomId, String cancelDate) {
		return milkDAO.inActiveFalt(roomId, cancelDate);
	}

	@Override
	public void dailyTimer() {
		String todayDate = AtUrHelpUtils.getTodayDate();
		List<GetFlatsData> mainList = new ArrayList<GetFlatsData>();
		
		// Get List of provider names
		List<String> providerList = milkDAO.getProviders();
		
		// Get data for each providers
		for (String provider : providerList) {
			List<GetFlatsData> flatsData = milkDAO.getMilkDetails(todayDate,
					provider);
			mainList.addAll(flatsData);
		}

		// update statements in batch
		milkDAO.updateMilkTimerData(mainList);
	}
	
	@Override
	public void testDailyTimer(String date) {
		List<GetFlatsData> mainList = new ArrayList<GetFlatsData>();
		
		// Get List of provider names
		List<String> providerList = milkDAO.getProviders();
		
		// Get data for each providers
		for (String provider : providerList) {
			List<GetFlatsData> flatsData = milkDAO.getMilkDetails(date,
					provider);
			mainList.addAll(flatsData);
		}

		// update statements in batch
		milkDAO.updateTestMilkTimerData(mainList, date);;
	}

	@Override
	public boolean updateMilkData(UpdateMilk um) {
		return milkDAO.updateMilkData(um);
	}

	@Override
	public boolean deleteMilkTimerData(Integer rid, Integer mid, String supplyDate) {
		return milkDAO.deleteMilkTimerData(rid, mid, supplyDate);
	}

	@Override
	public List<RoomBill> getBillByAppId(int appId, String fromDate, String toDate) {
		return milkDAO.getBillByAppId(appId, fromDate, toDate);
	}

	@Override
	public List<Priority> getPriorityListByRouteId(int routeId) {
		return milkDAO.getPriorityListByRouteId(routeId);
	}

	@Override
	public void updatePriority(List<Priority> priority) throws Exception{
		milkDAO.updatePriority(priority);
	}


}
