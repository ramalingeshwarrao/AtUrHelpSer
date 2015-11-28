package com.aturhelp.dao.util;

public class SQLQuery {

	public static final String INSERT_REGISTER = "INSERT INTO register (device_id, mobile_no, email, request_id, name) VALUES (?, ?, ?, ?, ?)";
	public static final String INSERT_REGISTER_ADMIN = "INSERT INTO register_admins (fullname, email, mobileno, ticket_status) values (?, ?, ?, ?)";
	public static final String UPDATE_REGISTER = "UPDATE register set mobile_no=?, email=?, name=? where device_id=?";
	public static final String GET_REGISTER_DETAILS = "SELECT device_id, mobile_no, email, request_id, name FROM register where device_id =?";
	public static final String GET_REGISTER_DETAILS_BY_DEVICE_ID = "SELECT device_id FROM register where device_id =?";
	public static final String LOG_TICKET = "INSERT INTO log (device_id, ser_id, log_sub, log_description, ticket_id, ticket_status) values (?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_TICKET = "UPDATE log set ticket_status=? where ticket_id=?";
	public static final String GET_ADMIN_INFO_BY_DEVICE_ID = "SELECT ser_id, device_id, mobile_no, email, request_id, name FROM admins where device_id=?";
	public static final String INSERT_ADMIN = "INSERT INTO admins (ser_id, device_id, mobile_no, email, request_id, name) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String GET_ADMIN_INFO_BY_SERVICE_ID = "SELECT adm.ser_id, adm.device_id, r.mobile_no, r.email, r.request_id, r.name FROM admins adm INNER JOIN register r where adm.device_id = r.device_id and adm.ser_id=?";
	public static final String GET_ADMIN_INFO_BY_ID = "SELECT ser_id, device_id, mobile_no, email, request_id, name, is_active FROM admins where id=?";
	public static final String UPDATE_ADMIN_STATE = "UPDATE admins SET is_active=? ,device_id=? WHERE id=?";
	public static final String GET_LOG_DATA_FOR_ADMIN = "SELECT log_sub, log_description, ticket_id, mobile_no, email, area from log l  inner join register reg where l.device_id=reg.device_id AND  ser_id IN (SELECT ser_id FROM admins where device_id=?) AND ticket_status=?";
	public static final String GET_LOG_DATA_FOR_CUSTOMER = "SELECT log_sub, log_description, ticket_id, ticket_status from log where device_id=? ORDER BY ticket_id desc limit 5";
	public static final String GET_DEVICE_ID_FROM_TICKET_ID = "SELECT device_id FROM log where ticket_id=?";
	public static final String GET_PASSWORD_BY_USER_ID = "SELECT password FROM admins where name=?";
	public static final String GET_ADMIN_BOOT_STRAP_DETAILS = "SELECT user_type, ser_id, area FROM admins WHERE name=?";
	public static final String GET_ADMIN_LOG_SUPER_USER = "SELECT log_sub, log_description, ticket_id, mobile_no, email, l.area from log l INNER JOIN admins ad where l.ser_id = ad.ser_id AND l.ticket_status=? AND ad.ser_id=? AND name=?";
	public static final String GET_ADMIN_LOG = "SELECT log_sub, log_description, ticket_id, mobile_no, email, l.area from log l INNER JOIN admins ad where l.ser_id = ad.ser_id AND l.area = ad.user_type AND l.ticket_status=? AND ad.ser_id=? AND ad.user_type=?";
}
