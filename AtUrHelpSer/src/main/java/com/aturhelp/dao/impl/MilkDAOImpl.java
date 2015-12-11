package com.aturhelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.aturhelp.common.Location;
import com.aturhelp.common.milk.Appartment;
import com.aturhelp.common.milk.FlatNo;
import com.aturhelp.common.milk.MilkPackets;
import com.aturhelp.common.milk.RoomMilk;
import com.aturhelp.common.milk.Route;
import com.aturhelp.dao.MilkDAO;
import com.aturhelp.dao.util.SQLQuery;

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
					ps.setFloat(2, milkPackets.getCost());
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
					ps.setInt(2, flatNo.getRouteNo());
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
					ps.setString(1, location.getLocName());
					ps.setString(2, location.getLocId());
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
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create room milk", e);
			return false;
		}
		return true;
	}

}
