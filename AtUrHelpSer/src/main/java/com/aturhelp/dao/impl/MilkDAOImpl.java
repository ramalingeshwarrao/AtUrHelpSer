package com.aturhelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.aturhelp.common.UpdateMilk;
import com.aturhelp.common.UserInfo;
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
import com.aturhelp.dao.util.SQLQuery;
import com.aturhelp.utils.AtUrHelpUtils;

@Component
public class MilkDAOImpl extends BaseDAO implements MilkDAO{
	
	final static Logger LOG = Logger.getLogger(MilkDAOImpl.class);

	@Override
	public boolean craetePackets(final MilkPackets milkPackets) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_MILK_PACKETS);
					ps.setString(1, milkPackets.getSubject());
					ps.setString(2, milkPackets.getMilkName());
					ps.setFloat(3, milkPackets.getCost());
					ps.setString(4, AtUrHelpUtils.getLoggedUserName());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create packet", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean createAppartment(final Appartment appartment) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_APPARTMENT);
					ps.setString(1, appartment.getAppSubject());
					ps.setString(2, appartment.getAppName());
					ps.setInt(3, appartment.getRouteId());
					ps.setString(4, AtUrHelpUtils.getLoggedUserName());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create appartment", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean createFlatNo(final FlatNo flatNo) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_FLAT_NO);
					ps.setString(1, flatNo.getRoomno());
					ps.setInt(2, flatNo.getAppId());
					ps.setString(3, AtUrHelpUtils.getLoggedUserName());
					ps.setBoolean(4, true);
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create flatno", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean createLocation(final Location location) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_LOCATION);
					ps.setString(1, location.getSubject());
					ps.setString(2, location.getName());
					ps.setString(3, AtUrHelpUtils.getLoggedUserName());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create location", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean createRoomMilk(final RoomMilk roomMilk) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_ROOM_MILK);
					ps.setInt(1, roomMilk.getRoomId());
					ps.setInt(2, roomMilk.getMilkId());
					ps.setInt(3, roomMilk.getQuantity());
					ps.setString(4, AtUrHelpUtils.getLoggedUserName());
					ps.setString(5, "0");
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create room milk", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean createRoute(final Route route) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_ROUTE);
					ps.setString(1, route.getSubject());
					ps.setString(2, route.getRouteId());
					ps.setString(3, AtUrHelpUtils.getLoggedUserName());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create route", e);
			return false;
		}
		return true;
	}

	@Override
	public List<MilkPackets> getMilkPackets() {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			MilkPackets milkP = new MilkPackets();
			milkP.setId(0);
			milkP.setMilkName("SELECT");
			milkP.setSubject("SELECT");
			List<MilkPackets> list = this.jdbcTemplate.query(
					SQLQuery.GET_MILK_PACKETS, new Object[] {providerName},
					new RowMapper<MilkPackets>() {
						@Override
						public MilkPackets mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							MilkPackets milkPackets = new MilkPackets();
							milkPackets.setSubject(rs.getString("subject"));
							milkPackets.setMilkName(rs.getString("milkid"));
							milkPackets.setCost(rs.getFloat("cost"));
							milkPackets.setId(rs.getInt("id"));
							return milkPackets;
						}
					});
			if (list != null && list.size() > 0) {
				list.add(0, milkP);
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get milk packet details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<Appartment> getAppartments(String rotueId) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			Appartment selectApp = new Appartment();
			selectApp.setId(0);
			selectApp.setAppName("SELECT");
			selectApp.setAppSubject("SELECT");
			
			String query = "";
			Object[] obj = null;
			if (StringUtils.isNotBlank(rotueId)) {
				query = SQLQuery.GET_APPARTMENTS_BY_ID;
				obj = new Object[]{rotueId, providerName};
			} else {
				query = SQLQuery.GET_APPARTMENTS;
				obj = new Object[]{providerName};
			}
			List<Appartment> list = this.jdbcTemplate.query(
					query, obj,
					new RowMapper<Appartment>() {
						@Override
						public Appartment mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Appartment appartment = new Appartment();
							appartment.setAppName(rs.getString("name"));
							appartment.setAppSubject(rs.getString("subject"));
							appartment.setId(rs.getInt("id"));
							return appartment;
						}
					});
			if (list != null && list.size() > 0) {
				list.add(0, selectApp);
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get appartment details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<Location> getLocations() {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<Location> list = this.jdbcTemplate.query(
					SQLQuery.GET_LOCATIONS, new Object[] {providerName},
					new RowMapper<Location>() {
						@Override
						public Location mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Location location = new Location();
							location.setSubject(rs.getString("subject"));
							location.setName(rs.getString("name"));
							return location;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get location details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<Route> getRoutes(Boolean isSelReq) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			Route selectRoute = null;
			if (isSelReq) {
				selectRoute = new Route();
				selectRoute.setId(0);
				selectRoute.setSubject("SELECT");
				selectRoute.setRouteId("SELECT");				
			}
			
			List<Route> list = this.jdbcTemplate.query(
					SQLQuery.GET_ROUTES, new Object[] {providerName},
					new RowMapper<Route>() {
						@Override
						public Route mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Route route = new Route();
							route.setId(rs.getInt("id"));
							route.setSubject(rs.getString("subject"));
							route.setRouteId(rs.getString("route_id"));
							return route;
						}
					});
			if (list != null && list.size() > 0) {
				if (isSelReq) {
					list.add(0, selectRoute);					
				}
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get route details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<GetFlatsData> getFlatDetails(String recordsPerPage, String fromRecord) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<GetFlatsData> list = this.jdbcTemplate.query(
					SQLQuery.GET_FALT_NOS_IN_APP, new Object[] {providerName, Integer.parseInt(fromRecord), Integer.parseInt(recordsPerPage)},
					new RowMapper<GetFlatsData>() {
						@Override
						public GetFlatsData mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							GetFlatsData flatsData = new GetFlatsData();
							flatsData.setAppartmentSubject(rs.getString("appsubject"));
							flatsData.setAppartmentName(rs.getString("name"));
							flatsData.setRoomId(rs.getString("room_id"));
							flatsData.setRouteName(rs.getString("route_id"));
							flatsData.setMilkId(rs.getString("milkid"));
							flatsData.setCost(rs.getFloat("cost"));
							flatsData.setQuantity(rs.getInt("quantity"));
							flatsData.setComment(rs.getString("comments"));
							return flatsData;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get flat details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<FlatNo> getFlatNoDetails(String flatNoId) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			FlatNo selectFaltData = new FlatNo();
			selectFaltData.setRoomno("SELECT");
			
			String query = "";
			Object[] obj = null;
			if (StringUtils.isNotBlank(flatNoId)) {
				query = SQLQuery.GET_FLAT_N0_BY_AP_ID;
				obj = new Object[]{flatNoId, providerName};
			} else {
				query = SQLQuery.GET_FLAT_NO;
				obj = new Object[]{providerName};
			}
			List<FlatNo> list = this.jdbcTemplate.query(
					query, obj,
					new RowMapper<FlatNo>() {
						@Override
						public FlatNo mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							FlatNo flatNo = new FlatNo();
							flatNo.setRoomno(rs.getString("room_id"));
							flatNo.setAppId(rs.getInt("app_id"));
							flatNo.setId(rs.getInt("id"));
							return flatNo;
						}
					});
			if (list != null && list.size() > 0) {
				list.add(0, selectFaltData);
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get appartment details", e);
			return null;
		}
		return null;
	}
	
	@Override
	public Integer getMilkCount() {
		String providerName = AtUrHelpUtils.getLoggedUserName();
		String query = null;
		Object[] obj = null;
		query = SQLQuery.GET_COUNT_MILK_DATA;
		obj = new Object[] {providerName};
		List<Integer> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getInt(1);
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean craeteNoMilk(final NoMilk noMilk) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					try {
						String query = SQLQuery.INSERT_NO_MILK_NO_TODATE;
						if (StringUtils.isNotBlank(noMilk.getToDate())) {
							query = SQLQuery.INSERT_NO_MILK;
						}
						PreparedStatement ps = con
								.prepareStatement(query);
						ps.setDate(1, new java.sql.Date(AtUrHelpUtils.getDate(noMilk.getFormDate()).getTime()));
						ps.setInt(2, noMilk.getRid());
						if (StringUtils.isNotBlank(noMilk.getToDate())) {
							ps.setBoolean(3, true);
							ps.setDate(4, new java.sql.Date(AtUrHelpUtils.getDate(noMilk.getToDate()).getTime()));
							ps.setString(5, AtUrHelpUtils.getLoggedUserName());
						} else {
							ps.setBoolean(3, false);
							ps.setString(4, AtUrHelpUtils.getLoggedUserName());
						}
						return ps;	
					} catch (Exception e) {
						throw new SQLException("Fail to insert record");
					}
					
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create route", e);
			return false;
		}
		return true;
	}

	@Override
	public Boolean getMilkStatusByRid(int roomId) {
		String providerName = AtUrHelpUtils.getLoggedUserName();
		String query = null;
		Object[] obj = null;
		query = SQLQuery.GET_STATUS_ROOM_MILK;
		obj = new Object[] {roomId, providerName };
		List<Boolean> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Boolean>() {
					@Override
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getBoolean("isUpdated");
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	//We are using this method for scheduler
	@Override
	public List<GetFlatsData> getMilkDetails(String strDate, String providerName) {
		try {
			List<GetFlatsData> list = this.jdbcTemplate.query(
					SQLQuery.GET_DAY_MILK, new Object[] {new java.sql.Date(AtUrHelpUtils.getDate(strDate).getTime()), new java.sql.Date(AtUrHelpUtils.getDate(strDate).getTime()), new java.sql.Date(AtUrHelpUtils.getDate(strDate).getTime()),providerName, providerName},
					new RowMapper<GetFlatsData>() {
						@Override
						public GetFlatsData mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							GetFlatsData flatsData = new GetFlatsData();
							flatsData.setAppartmentSubject(rs.getString("appsubject"));
							flatsData.setAppartmentName(rs.getString("name"));
							flatsData.setRoomId(rs.getString("room_id"));
							flatsData.setRouteName(rs.getString("route_id"));
							flatsData.setMilkId(rs.getString("milkid"));
							flatsData.setCost(rs.getFloat("cost"));
							flatsData.setQuantity(rs.getInt("quantity"));
							int cat = rs.getInt("is_alternative");
							String category = null;
							if (cat == 1) {
								category = com.aturhelp.common.Category.ALTERNATE.getCategory();
							} else {
								category = com.aturhelp.common.Category.GENERAL.getCategory();
							}
							flatsData.setProviderName(rs.getString("provider_id"));
							flatsData.setCategory(category);
							return flatsData;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get flat details", e);
			return null;
		}
		return null;
	}
	
	@Override
	public List<GetFlatsData> getMilkDetailsByRouteId(int routeId, String strDate) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<GetFlatsData> list = this.jdbcTemplate.query(
					SQLQuery.GET_TIMER_DAY_MILK_BY_ROUTE_ID, new Object[] {new java.sql.Date(AtUrHelpUtils.getDate(strDate).getTime()), routeId, providerName},
					new RowMapper<GetFlatsData>() {
						@Override
						public GetFlatsData mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							GetFlatsData flatsData = new GetFlatsData();
							flatsData.setAppartmentSubject(rs.getString("appsubject"));
							flatsData.setAppartmentName(rs.getString("name"));
							flatsData.setRoomId(rs.getString("room_id"));
							flatsData.setRouteName(rs.getString("route_id"));
							flatsData.setMilkId(rs.getString("milkid"));
							flatsData.setCost(rs.getFloat("cost"));
							flatsData.setQuantity(rs.getInt("quantity"));
							flatsData.setFlatPrimaryKey(rs.getString("mfnid"));
							flatsData.setPacketPrimaryKey(rs.getString("pid"));
							return flatsData;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get flat details", e);
			return null;
		}
		return null;
	}

	@Override
	public GetFlatsData getNoMilkDetails(int roomid, int appId) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<GetFlatsData> list = this.jdbcTemplate.query(
					SQLQuery.GET_NO_MILK_BY_ROOM_ID_APP_ID, new Object[] {false, appId, roomid, providerName},
					new RowMapper<GetFlatsData>() {
						@Override
						public GetFlatsData mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							GetFlatsData flatsData = new GetFlatsData();
							flatsData.setNoMilkId(rs.getInt("mn.id"));
							flatsData.setAppartmentName(rs.getString("ma.name"));
							flatsData.setRoomId(rs.getString("mfn.room_id"));
							flatsData.setDate(rs.getString("mn.fromdate"));
							return flatsData;
						}
					});
			if (list != null && list.size() > 0) {
				return	list.get(0);
			}
		} catch (Exception e) {
			LOG.error("Fail to get flat details", e);
			return null;
		}
		return null;
	}

	@Override
	public boolean updateNoMilkToGetMilk(final int roomId, final String toDate) {
		try {
			final String providerName = AtUrHelpUtils.getLoggedUserName();
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					try {
						PreparedStatement ps = con
								.prepareStatement(SQLQuery.UPDATE_NO_MILK_TO_GET_MILK);
						ps.setBoolean(1, true);
						ps.setDate(2, new java.sql.Date(AtUrHelpUtils.getDate(toDate).getTime()));
						ps.setInt(3, roomId);
						ps.setString(4, providerName);
						return ps;						
					} catch (Exception e) {
						throw new SQLException("Fail to update record");
					}
					
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to update ticket", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean noMilkFirstCase(NoMilk nomilk) {
		try {
		String query = null;
		Object[] obj = null;
		String providerName = AtUrHelpUtils.getLoggedUserName();
		query = SQLQuery.NO_MILK_FIRST_CASE;
		obj = new Object[] {nomilk.getRid(),  new java.sql.Date(AtUrHelpUtils.getDate(nomilk.getFormDate()).getTime()), false, providerName};
		List<Boolean> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Boolean>() {
					@Override
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						int returnVal = rs.getInt(1);
						return (returnVal == 0) ? false : true;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return false; //Means no records
		} catch (Exception e) {
			LOG.error("Fail to get firstcase ", e);
			return false;
		}
	}

	@Override
	public boolean noMilkSecondCase(NoMilk nomilk) {
		try {
		String query = null;
		Object[] obj = null;
		String providerName = AtUrHelpUtils.getLoggedUserName();
		query = SQLQuery.NO_MILK_SECOND_CASE;
		obj = new Object[] {nomilk.getRid(),  new java.sql.Date(AtUrHelpUtils.getDate(nomilk.getFormDate()).getTime()), new java.sql.Date(AtUrHelpUtils.getDate(nomilk.getFormDate()).getTime()), true, providerName};
		List<Boolean> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Boolean>() {
					@Override
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						int returnVal = rs.getInt(1);
						return (returnVal == 0) ? false : true;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return false; //Means no records
		} catch(Exception e) {
			LOG.error("Fail to get second case ", e);
			return false;
		}
	}

	@Override
	public List<GetFlatsData> getAllNoMilkDetails(int roomId, int appId) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<GetFlatsData> list = this.jdbcTemplate.query(
					SQLQuery.GET_ALL_NO_MILK_BY_ROOM_ID_APP_ID, new Object[] {appId, roomId, providerName},
					new RowMapper<GetFlatsData>() {
						@Override
						public GetFlatsData mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							GetFlatsData flatsData = new GetFlatsData();
							flatsData.setAppartmentName(rs.getString("ma.name"));
							flatsData.setRoomId(rs.getString("mfn.room_id"));
							flatsData.setDate(rs.getString("mn.fromdate"));
							flatsData.setTodate(rs.getString("mn.todate"));
							return flatsData;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get flat details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<NoMilkCost> getMilkCostForAllFlatByApp(int appId) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<NoMilkCost> list = this.jdbcTemplate.query(
					SQLQuery.GET_MILK_COST_BY_APP_ID, new Object[] {appId, providerName},
					new RowMapper<NoMilkCost>() {
						@Override
						public NoMilkCost mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							NoMilkCost nMC = new NoMilkCost();
							nMC.setId(rs.getInt("mfn.id"));
							nMC.setRoomId(rs.getString("mfn.room_id"));
							nMC.setCost(rs.getInt("cost"));
							nMC.setQuantity(rs.getInt("quantity"));
							return nMC;
						}
					});
			if (list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get milk cost details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<NoMilk> getNoMilkDetailsById(NoMilk noMilk) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<NoMilk> list = this.jdbcTemplate.query(
					SQLQuery.GET_NO_MILK_FOR_COST_BY_RID, new Object[] {new java.sql.Date(AtUrHelpUtils.getDate(noMilk.getFormDate()).getTime()), new java.sql.Date(AtUrHelpUtils.getDate(noMilk.getToDate()).getTime()), noMilk.getRid(), providerName},
					new RowMapper<NoMilk>() {
						@Override
						public NoMilk mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							NoMilk noMilk = new NoMilk();
							noMilk.setFormDate(rs.getString("fromdate"));
							noMilk.setToDate(rs.getString("todate"));
							return noMilk;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get nomilk details by id details", e);
			return null;
		}
		return null;
	}

	@Override
	public NoMilk getNoMilkDetailsByIdForNull(NoMilk noMilk) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<NoMilk> list = this.jdbcTemplate.query(
					SQLQuery.GET_NO_MILK_FOR_COST_BY_RID_NULL, new Object[] {noMilk.getRid(), providerName},
					new RowMapper<NoMilk>() {
						@Override
						public NoMilk mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							NoMilk noMilk = new NoMilk();
							noMilk.setFormDate(rs.getString("fromdate"));
							return noMilk;
						}
					});
			if (list != null && list.size() > 0) {
				return	list.get(0); //Only one record will be there
			}
		} catch (Exception e) {
			LOG.error("Fail to get nomilk details by id details", e);
			return null;
		}
		return null;
	}

	@Override
	public boolean noMilkFirstCaseIfTODateNull(NoMilk nomilk) {
		try {
		String query = null;
		Object[] obj = null;
		String providerName = AtUrHelpUtils.getLoggedUserName();
		query = SQLQuery.NO_MILK_FIRST_CASE_IF_TO_DATE_NULL;
		obj = new Object[] {nomilk.getRid(),  new java.sql.Date(AtUrHelpUtils.getDate(nomilk.getFormDate()).getTime()), providerName};
		List<Boolean> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Boolean>() {
					@Override
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						int returnVal = rs.getInt(1);
						return (returnVal == 0) ? false : true;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return false; //Means no records
		} catch (Exception e) {
			LOG.error("Fail to get firstcase ", e);
			return false;
		}
	}

	@Override
	public boolean noMilkValidateFDGreaterGivenTD(NoMilk nomilk) {
		try {
		String query = null;
		Object[] obj = null;
		String providerName = AtUrHelpUtils.getLoggedUserName();
		query = SQLQuery.NO_MILK_VALIDATE_FD_GD_GIVEN_TD;
		obj = new Object[] {nomilk.getRid(),  new java.sql.Date(AtUrHelpUtils.getDate(nomilk.getToDate()).getTime()), providerName};
		List<Boolean> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Boolean>() {
					@Override
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						int returnVal = rs.getInt(1);
						return (returnVal == 0) ? false : true;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return false; //Means no records
		} catch (Exception e) {
			LOG.error("Fail to get firstcase ", e);
			return false;
		}
	}

	@Override
	public boolean validateDateInRange(String date, int rid) {
		try {
		String query = null;
		Object[] obj = null;
		query = SQLQuery.VALIDATE_DATE_IN_RANGE;
		String providerName = AtUrHelpUtils.getLoggedUserName();
		obj = new Object[] {rid,  new java.sql.Date(AtUrHelpUtils.getDate(date).getTime()), new java.sql.Date(AtUrHelpUtils.getDate(date).getTime()), providerName};
		List<Boolean> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Boolean>() {
					@Override
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						int returnVal = rs.getInt(1);
						return (returnVal == 0) ? false : true;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return false; //Means no records
		} catch (Exception e) {
			LOG.error("Fail to get firstcase ", e);
			return false;
		}
	}

	@Override
	public boolean validateRangeInRange(int rid, String fromDate, String toDate) {
		try {
		String query = null;
		Object[] obj = null;
		query = SQLQuery.VALIDATE_RANGE_IN_RANGE;
		String providerName = AtUrHelpUtils.getLoggedUserName();
		obj = new Object[] {rid,  new java.sql.Date(AtUrHelpUtils.getDate(fromDate).getTime()), new java.sql.Date(AtUrHelpUtils.getDate(toDate).getTime()), providerName};
		List<Boolean> list = this.jdbcTemplate.query(query, obj,
				new RowMapper<Boolean>() {
					@Override
					public Boolean mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						int returnVal = rs.getInt(1);
						return (returnVal == 0) ? false : true;
					}
				});
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return false; //Means no records
		} catch (Exception e) {
			LOG.error("Fail to get firstcase ", e);
			return false;
		}
	}

	@Override
	public List<BalanceSheet> getMilkSpendinLts(String strDate) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<BalanceSheet> list = this.jdbcTemplate.query(
					SQLQuery.GET_CONSUMED_MILK, new Object[] {providerName, new java.sql.Date(AtUrHelpUtils.getDate(strDate).getTime())},
					new RowMapper<BalanceSheet>() {
						@Override
						public BalanceSheet mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							BalanceSheet balanceSheet = new BalanceSheet();
							balanceSheet.setCategory(rs.getString("milkid"));
							balanceSheet.setLiters(rs.getFloat("liters"));
							return balanceSheet;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get nomilk details by id details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<BalanceSheet> getMilkSpendinLtsByRoute(String strDate) {
		try {
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<BalanceSheet> list = this.jdbcTemplate.query(
					SQLQuery.GET_CONSUMED_MILK_BY_ROUTE_ID, new Object[] {providerName, new java.sql.Date(AtUrHelpUtils.getDate(strDate).getTime())},
					new RowMapper<BalanceSheet>() {
						@Override
						public BalanceSheet mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							BalanceSheet balanceSheet = new BalanceSheet();
							balanceSheet.setCategory(rs.getString("milkid"));
							balanceSheet.setLiters(rs.getFloat("liters"));
							balanceSheet.setRoute(rs.getString("route_id"));
							return balanceSheet;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get nomilk details by id details", e);
			return null;
		}
		return null;
	}

	@Override
	public boolean createCategory(final Category cat) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_CATEGORY);
					ps.setString(1, cat.getCategory());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create route", e);
			return false;
		}
		return true;
	}

	@Override
	public List<Category> getCategories() {
		try {
			Category selectCat = new Category();
			selectCat.setCategory("SELECT");
			
			List<Category> list = this.jdbcTemplate.query(
					SQLQuery.GET_CATEGORIES, new Object[] {},
					new RowMapper<Category>() {
						@Override
						public Category mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Category cat = new Category();
							cat.setCategory(rs.getString("name"));
							return cat;
						}
					});
			if (list != null && list.size() > 0) {
				list.add(0, selectCat);
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get cat details", e);
			return null;
		}
		return null;
	}

	@Override
	public boolean setComment(final String roomId, final String comment) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.UPDATE_COMMENT_FOR_FLAT);
					ps.setString(1, comment);
					ps.setString(2, roomId);
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to update admin state", e);
			return false;
		}

		return true;
	}

	@Override
	public List<Comment> getComment(String roomId) {
		try {
			
			List<Comment> list = this.jdbcTemplate.query(
					SQLQuery.GET_COMMENT, new Object[] {roomId},
					new RowMapper<Comment>() {
						@Override
						public Comment mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Comment cm = new Comment();
							String name = rs.getString("comments");
							cm.setName(name);
							return cm;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get cat details", e);
			return null;
		}
		return null;
	}

	@Override
	public List<MilkPackets> getMilkByRoomId(String roomId) {
		try {
			MilkPackets milkP = new MilkPackets();
			milkP.setId(0);
			milkP.setMilkName("SELECT");
			milkP.setSubject("SELECT");
			String providerName = AtUrHelpUtils.getLoggedUserName();
			List<MilkPackets> list = this.jdbcTemplate.query(
					SQLQuery.GET_MILK_BY_ROOM_ID, new Object[] {roomId, providerName},
					new RowMapper<MilkPackets>() {
						@Override
						public MilkPackets mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							MilkPackets mp = new MilkPackets();
							mp.setId(rs.getInt("id"));
							mp.setSubject(rs.getString("subject"));
							mp.setMilkName(rs.getString("milkid"));
							mp.setCost(rs.getFloat("cost"));
							return mp;
						}
					});
			if (list != null && list.size() > 0) {
				list.add(0, milkP);
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get milk details by room id details", e);
			return null;
		}
		return null;
	}

	@Override
	public int getCountByMilkId(String roomId, String milkId) {
		try {
			List<Integer> list = this.jdbcTemplate.query(
					SQLQuery.GET_QUANTITY_BY_ROOMID_MILK_ID, new Object[] {roomId, milkId},
					new RowMapper<Integer>() {
						@Override
						public Integer mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getInt("quantity");
						}
					});
			if (list != null && list.size() > 0) {
				return	list.get(0);
			}
		} catch (Exception e) {
			LOG.error("Fail to get milk details by room id details", e);
			return 0;
		}
		return 0;
	}

	@Override
	public boolean updateIsAlternativeToTrue(final String roomId, final String milkId, final int alterCount) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.UPDATE_IS_ALTERNATIVE);
					ps.setInt(1, 1);
					ps.setInt(2, alterCount);
					ps.setString(3, roomId);
					ps.setString(4, milkId);
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to update alternative is required", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean inActiveFalt(final String roomId, final String cancelDate) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					try {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.UPDATE_FLAT_IN_ACTIVE);
					ps.setInt(1, 0);
					ps.setDate(2, new java.sql.Date(AtUrHelpUtils.getDate(cancelDate).getTime()));
					ps.setString(3, roomId);
					return ps;
					}
					catch (Exception e ) {
						throw new SQLException("Fail to update record");
					}
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to update inActive", e);
			return false;
		}
		return true;
	}

	@Override
	public void updateMilkTimerData(final List<GetFlatsData> flatsData) {

		try {

			// To improve performance we need executing batch of statements
			String sql = SQLQuery.INSERT_TIMER_DATA;
			final String date = AtUrHelpUtils.getCurrentDate();

			this.jdbcTemplate.batchUpdate(sql,
					new BatchPreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							try {
								GetFlatsData flatData = flatsData.get(i);
								ps.setString(1, flatData.getCategory());
								ps.setString(2, flatData.getRoomId());
								ps.setString(3, flatData.getMilkId());
								ps.setInt(4, flatData.getQuantity());
								ps.setDate(5, new java.sql.Date(AtUrHelpUtils.getDate(date).getTime()));
								ps.setString(6, flatData.getProviderName());
								
							} catch (Exception e) {
								LOG.error("Fail to update record", e);
							}
						}

						@Override
						public int getBatchSize() {
							return flatsData.size();
						}
					});
		} catch (Exception e) {
			LOG.error("Fail to insert batch statements", e);
		}
	}

	@Override
	public List<String> getProviders() {
		try {
			List<String> list = this.jdbcTemplate.query(SQLQuery.GET_PROVIDERS,
					new Object[] {}, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString(1);
						}
					});
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get milk details by room id details", e);
			return null;
		}
		return null;
	}

	@Override
	public boolean updateMilkData(final UpdateMilk um) {
		try {
			updateRoomMilkData(um);
			updateTimerMilkData(um);
		} catch (Exception e) {
			LOG.error("Fail to edit data", e);
			return false;
		}
		return true;
	}
	
	public boolean updateRoomMilkData(final UpdateMilk um) {
		//Update Room milk data
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				try {
				PreparedStatement ps = null;
				if (um.getNewMilkId() != null && um.getNewQuantity() != null) {
					ps = con.prepareStatement(SQLQuery.EDIT_M_ROOM_MID_QTY);  
					ps.setInt(1, um.getNewMilkId());
					ps.setInt(2, um.getNewQuantity());
					ps.setInt(3, um.getMilkRoomRoomId());
					ps.setInt(4, um.getMilkRoomMilkId());
				} else if (um.getNewMilkId() != null) {
					ps = con.prepareStatement(SQLQuery.EDIT_M_ROOM_MID);  
					ps.setInt(1, um.getNewMilkId());
					ps.setInt(2, um.getMilkRoomRoomId());
					ps.setInt(3, um.getMilkRoomMilkId());
				} else if (um.getNewQuantity() != null) {
					ps = con.prepareStatement(SQLQuery.EDIT_M_ROOM_QTY);  
					ps.setInt(1, um.getNewQuantity());
					ps.setInt(2, um.getMilkRoomRoomId());
					ps.setInt(3, um.getMilkRoomMilkId());
				}
				return ps;
				}
				catch (Exception e ) {
					throw new SQLException("Fail to update record");
				}
			}
		});
		return true;
	}
	
	public boolean updateTimerMilkData(final UpdateMilk um) {
		//Update Timer milk data
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				try {
				PreparedStatement ps = null;
				if (um.getNewMilkId() != null && um.getNewQuantity() != null) {
					ps = con.prepareStatement(SQLQuery.EDIT_TIMER_MID_QTY);  
					ps.setInt(1, um.getNewMilkId());
					ps.setInt(2, um.getNewQuantity());
					ps.setInt(3, um.getMilkRoomRoomId());
					ps.setInt(4, um.getMilkRoomMilkId());
					ps.setDate(5, new java.sql.Date(AtUrHelpUtils.getDate(um.getSupplyDate()).getTime()));
				} else if (um.getNewMilkId() != null) {
					ps = con.prepareStatement(SQLQuery.EDIT_TIMER_MID);  
					ps.setInt(1, um.getNewMilkId());
					ps.setInt(2, um.getMilkRoomRoomId());
					ps.setInt(3, um.getMilkRoomMilkId());
					ps.setDate(4, new java.sql.Date(AtUrHelpUtils.getDate(um.getSupplyDate()).getTime()));
				} else if (um.getNewQuantity() != null) {
					ps = con.prepareStatement(SQLQuery.EDIT_TIMER_QTY);  
					ps.setInt(1, um.getNewQuantity());
					ps.setInt(2, um.getMilkRoomRoomId());
					ps.setInt(3, um.getMilkRoomMilkId());
					ps.setDate(4, new java.sql.Date(AtUrHelpUtils.getDate(um.getSupplyDate()).getTime()));
				}
				return ps;
				}
				catch (Exception e ) {
					throw new SQLException("Fail to update record");
				}
			}
		});
		return true;
	}

	@Override
	public boolean deleteMilkTimerData(final Integer rid, final Integer mid, final String supplyDate) {
		final String providerName = AtUrHelpUtils.getLoggedUserName();
		//Update Timer milk data
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				try {
					PreparedStatement ps = null;
					ps = con.prepareStatement(SQLQuery.DELETE_MILK_TIMER_DATA);  
					ps.setInt(1, rid);
					ps.setInt(2, mid);
					ps.setDate(3, new java.sql.Date(AtUrHelpUtils.getDate(supplyDate).getTime()));
					ps.setString(4, providerName);
				return ps;
				}
				catch (Exception e ) {
					throw new SQLException("Fail to update record");
				}
			}
		});
		return true;
	}

	@Override
	public List<RoomBill> getBillByAppId(final int appId, final String fromDate, final String toDate) {
		final String providerName = AtUrHelpUtils.getLoggedUserName();
		try {
			List<RoomBill> list = this.jdbcTemplate.query(SQLQuery.GET_BILL_BY_APP_ID,
					new Object[] {new java.sql.Date(AtUrHelpUtils.getDate(fromDate).getTime()), new java.sql.Date(AtUrHelpUtils.getDate(toDate).getTime()), providerName, appId}, new RowMapper<RoomBill>() {
						@Override
						public RoomBill mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							RoomBill rb = new RoomBill();
							rb.setAppName(rs.getString("subject"));
							rb.setCost(rs.getInt("finalcost"));
							rb.setRoomId(rs.getString("room_id"));
							return rb;
						}
					});
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get milk details by room id details", e);
			return null;
		}
		return null;
	
	}

	@Override
	public List<Priority> getPriorityListByRouteId(final int routeId) {
		try {
			List<Priority> list = this.jdbcTemplate.query(SQLQuery.GET_APP_ID_PRIORITY_BY_ROUTE_ID,
					new Object[] {routeId}, new RowMapper<Priority>() {
						@Override
						public Priority mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Priority p = new Priority();
							p.setAppName(rs.getString("appname"));
							p.setAppId(rs.getInt("appid"));
							p.setPriorityId(rs.getInt("priority"));
							return p;
						}
					});
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get milk details by room id details", e);
			return null;
		}
		return null;
	
	}

	@Override
	public void updatePriority(final List<Priority> priority) throws Exception{

		try {
			String sql = SQLQuery.UPDATE_PRIORITY;
			this.jdbcTemplate.batchUpdate(sql,
					new BatchPreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps, int i)
								throws SQLException {
							try {
								Priority p = priority.get(i);
								ps.setInt(1, p.getPriorityId());
								ps.setInt(2, p.getAppId());
							} catch (Exception e) {
								LOG.error("Fail to update record", e);
							}
						}
						@Override
						public int getBatchSize() {
							return priority.size();
						}
					});
		} catch (Exception e) {
			LOG.error("Fail to insert batch statements", e);
			throw new Exception("Fail to update priority");
		}
	}

}
