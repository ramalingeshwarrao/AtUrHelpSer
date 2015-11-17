package com.aturhelp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.aturhelp.common.AdminInfo;
import com.aturhelp.common.Help;
import com.aturhelp.common.Location;
import com.aturhelp.common.Services;
import com.aturhelp.common.UserInfo;
import com.aturhelp.dao.HelpDAO;
import com.aturhelp.dao.util.SQLQuery;
import com.aturhelp.services.impl.HelpServiceImpl;

@Repository("helpDAOImpl")
public class HelpDAOImpl extends BaseDAO implements HelpDAO {
	
	final static Logger LOG = Logger.getLogger(HelpServiceImpl.class);

	@Override
	public UserInfo getUserData(String deviceId) {
		try {
			List<UserInfo> list = this.jdbcTemplate.query(
					SQLQuery.GET_REGISTER_DETAILS, new Object[] { deviceId },
					new RowMapper<UserInfo>() {
						@Override
						public UserInfo mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							UserInfo userInfo = new UserInfo();
							userInfo.setDeviceId(rs.getString("device_id"));
							userInfo.setMobileNo(rs.getString("mobile_no"));
							userInfo.setEmail(rs.getString("email"));
							userInfo.setRequestId(rs.getString("request_id"));
							userInfo.setName(rs.getString("name"));
							return userInfo;
						}
					});
			if (list != null && list.size() > 0) {
				return	list.get(0);
			}
		} catch (Exception e) {
			LOG.error("Fail to get register details", e);
			return null;
		}
		return null;
	}
	
	@Override
	public boolean isCustomerExist(String deviceId) {
		try {
			List<String> list = this.jdbcTemplate.query(
					SQLQuery.GET_REGISTER_DETAILS_BY_DEVICE_ID, new Object[] { deviceId },
					new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("device_id");
						}
					});
			if (list != null && list.size() > 0) {
				return	true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Services getServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUserInfo(final UserInfo userInfo) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_REGISTER);
					ps.setString(1, userInfo.getDeviceId());
					ps.setString(2, userInfo.getMobileNo());
					ps.setString(3, userInfo.getEmail());
					ps.setString(4, userInfo.getRequestId());
					ps.setString(5, userInfo.getName());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to insert register", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateUserInfo(final UserInfo userInfo) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.UPDATE_REGISTER);
					ps.setString(1, userInfo.getMobileNo());
					ps.setString(2, userInfo.getEmail());
					ps.setString(3, userInfo.getName());
					ps.setString(4, userInfo.getDeviceId());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to update register", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean createHelp(final Help help) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.LOG_TICKET);
					ps.setString(1, help.getDeviceId());
					ps.setString(2, help.getServiceId());
					ps.setString(3, help.getHelpType());
					ps.setString(4, help.getHelpDes());
					ps.setLong(5, help.getTicketNo());
					ps.setBoolean(6, false);
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create customer request", e);
			return false;
		}

		return true;
	}

	@Override
	public AdminInfo getAdminInfo(String deviceId) {

		try {
			List<AdminInfo> list = this.jdbcTemplate.query(
					SQLQuery.GET_ADMIN_INFO_BY_DEVICE_ID, new Object[] { deviceId },
					new RowMapper<AdminInfo>() {
						@Override
						public AdminInfo mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							AdminInfo adminInfo = new AdminInfo();
							adminInfo.setServiceId(rs.getString("ser_id"));
							adminInfo.setDeviceId(rs.getString("device_id"));
							adminInfo.setMobileNo(rs.getString("mobile_no"));
							adminInfo.setEmail(rs.getString("email"));
							adminInfo.setRequestId(rs.getString("request_id"));
							adminInfo.setName(rs.getString("name"));
							return adminInfo;
						}
					});
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (EmptyResultDataAccessException e) {
			LOG.error("Fail to get admin info", e);
			return null;
		}
		return null;
	}

	@Override
	public AdminInfo getAdminInfoByServiceId(String serviceId) {

		try {
			List<AdminInfo> list = this.jdbcTemplate.query(
					SQLQuery.GET_ADMIN_INFO_BY_SERVICE_ID, new Object[] { serviceId },
					new RowMapper<AdminInfo>() {
						@Override
						public AdminInfo mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							AdminInfo adminInfo = new AdminInfo();
							adminInfo.setServiceId(rs.getString("ser_id"));
							adminInfo.setDeviceId(rs.getString("device_id"));
							adminInfo.setMobileNo(rs.getString("mobile_no"));
							adminInfo.setEmail(rs.getString("email"));
							adminInfo.setRequestId(rs.getString("request_id"));
							adminInfo.setName(rs.getString("name"));
							return adminInfo;
						}
					});
			if (list != null && list.size() > 0) {
				list.get(0);
			}
			return list.get(0);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("Fail to get admin info", e);
			return null;
		}

	}

	@Override
	public boolean updateTicket(final Help help) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.UPDATE_TICKET);
					ps.setBoolean(1, true);
					ps.setLong(2, help.getTicketNo());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to update ticket", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean createAdmin(final AdminInfo admin) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_ADMIN);
					ps.setString(1, admin.getServiceId());
					ps.setString(2, admin.getDeviceId());
					ps.setString(3, admin.getMobileNo());
					ps.setString(4, admin.getEmail());
					ps.setString(5, admin.getRequestId());
					ps.setString(6, admin.getName());
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to create admin", e);
			return false;
		}
		return true;
	}

	@Override
	public AdminInfo getAdminInfoById(String id) {

		try {
			List<AdminInfo> list = this.jdbcTemplate.query(
					SQLQuery.GET_ADMIN_INFO_BY_ID, new Object[] { id },
					new RowMapper<AdminInfo>() {
						@Override
						public AdminInfo mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							AdminInfo adminInfo = new AdminInfo();
							adminInfo.setServiceId(rs.getString("ser_id"));
							adminInfo.setDeviceId(rs.getString("device_id"));
							adminInfo.setMobileNo(rs.getString("mobile_no"));
							adminInfo.setEmail(rs.getString("email"));
							adminInfo.setRequestId(rs.getString("request_id"));
							adminInfo.setName(rs.getString("name"));
							adminInfo.setIsActive(rs.getString("is_active"));
							return adminInfo;
						}
					});
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (EmptyResultDataAccessException e) {
			LOG.error("Fail to get admininfo by id", e);
			return null;
		}
		return null;
	}

	@Override
	public boolean updateAdminState(final String value, final String adminId, final String deviceId) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.UPDATE_ADMIN_STATE);
					ps.setString(1, value);
					ps.setString(2, deviceId);
					ps.setString(3, adminId);
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
	public List<Help> getLogData(String deviceId) {
		try {
			List<Help> list = this.jdbcTemplate.query(
					SQLQuery.GET_LOG_DATA_FOR_ADMIN, new Object[] { deviceId, false },
					new RowMapper<Help>() {
						@Override
						public Help mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Help help = new Help();
							help.setHelpType(rs.getString("log_sub"));
							help.setHelpDes(rs.getString("log_description"));
							help.setTicketNo(rs.getLong("ticket_id"));
							help.setMobileNo(rs.getString("mobile_no"));
							help.setMail(rs.getString("email"));
							return help;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get log data", e);
			return null;
		}
		return null;
	}

	@Override
	public List<Help> getCustomerLogData(String deviceId) {
		try {
			List<Help> list = this.jdbcTemplate.query(
					SQLQuery.GET_LOG_DATA_FOR_CUSTOMER, new Object[] { deviceId},
					new RowMapper<Help>() {
						@Override
						public Help mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Help help = new Help();
							help.setHelpType(rs.getString("log_sub"));
							help.setHelpDes(rs.getString("log_description"));
							help.setTicketNo(rs.getLong("ticket_id"));
							help.setStatus(rs.getInt("ticket_status"));
							return help;
						}
					});
			if (list != null && list.size() > 0) {
				return	list;
			}
		} catch (Exception e) {
			LOG.error("Fail to get customer log data", e);
			return null;
		}
		return null;
	}

	@Override
	public String getDeviceIdFromTicketId(String ticketNo) {
		try {
			List<String> list = this.jdbcTemplate.query(
					SQLQuery.GET_DEVICE_ID_FROM_TICKET_ID,
					new Object[] { ticketNo }, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("device_id");
						}
					});
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			LOG.error("Fail to get deviceid from ticket id", e);
			return null;
		}
		return null;
	}

	@Override
	public Boolean insertRegisterAdmin(final AdminInfo adminInfo) {
		try {
			this.jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con
							.prepareStatement(SQLQuery.INSERT_REGISTER_ADMIN);
					ps.setString(1, adminInfo.getName());
					ps.setString(2, adminInfo.getEmail());
					ps.setString(3, adminInfo.getMobileNo());
					ps.setBoolean(4, false);
					return ps;
				}
			});
		} catch (Exception e) {
			LOG.error("Fail to insert register admins", e);
			return false;
		}
		return true;
	}

	@Override
	public String getPassword(String userName) {
		try {
			List<String> list = this.jdbcTemplate.query(
					SQLQuery.GET_PASSWORD_BY_USER_ID,
					new Object[] { userName }, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("password");
						}
					});
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			LOG.error("Fail to get deviceid from ticket id", e);
			return null;
		}
		return null;
	}

}
