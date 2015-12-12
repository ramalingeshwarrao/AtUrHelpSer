package com.aturhelp.dao.util;

public class SQLQuery {

	//Broad Band Queries And admin queries
	public static final String INSERT_REGISTER = "INSERT INTO register (device_id, mobile_no, email, request_id, name) VALUES (?, ?, ?, ?, ?)";
	public static final String INSERT_REGISTER_ADMIN = "INSERT INTO register_admins (fullname, email, mobileno, ticket_status) values (?, ?, ?, ?)";
	public static final String UPDATE_REGISTER = "UPDATE register set mobile_no=?, email=?, name=? where device_id=?";
	public static final String GET_REGISTER_DETAILS = "SELECT device_id, mobile_no, email, request_id, name FROM register where device_id =?";
	public static final String GET_REGISTER_DETAILS_BY_DEVICE_ID = "SELECT device_id FROM register where device_id =?";
	public static final String LOG_TICKET = "INSERT INTO log (device_id, ser_id, log_sub, log_description, ticket_id, ticket_status, area) values (?, ?, ?, ?, ?, ?, ?)";
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
	public static final String GET_ADMIN_LOG_SUPER_USER_COUNT = "SELECT count(1) from log l INNER JOIN admins ad where l.ser_id = ad.ser_id AND l.ticket_status=? AND ad.ser_id=? AND name=?";
	public static final String GET_ADMIN_LOG_SUPER_USER_PAGENATING = "SELECT log_sub, log_description, ticket_id, mobile_no, email, l.area from log l INNER JOIN admins ad where l.ser_id = ad.ser_id AND l.ticket_status=? AND ad.ser_id=? AND name=? limit ?,?";
	public static final String GET_ADMIN_LOG = "SELECT log_sub, log_description, ticket_id, mobile_no, email, l.area from log l INNER JOIN admins ad where l.ser_id = ad.ser_id AND l.area = ad.user_type AND l.ticket_status=? AND ad.ser_id=? AND ad.user_type=?";
	public static final String GET_ADMIN_LOG_COUNT = "SELECT count(1) from log l INNER JOIN admins ad where l.ser_id = ad.ser_id AND l.area = ad.user_type AND l.ticket_status=? AND ad.ser_id=? AND ad.user_type=?";
	public static final String GET_ADMIN_LOG_PAGENATING = "SELECT log_sub, log_description, ticket_id, mobile_no, email, l.area from log l INNER JOIN admins ad where l.ser_id = ad.ser_id AND l.area = ad.user_type AND l.ticket_status=? AND ad.ser_id=? AND ad.user_type=? limit ?,?";
	public static final String GET_ADMIN_PROFILE = "SELECT mobile_no, email, user_type, is_active, place, department,  gender, name FROM admins where name=?";
	
	//MilkQueries
	
	//Insert Queries
	public static final String INSERT_APPARTMENT = "INSERT INTO milk_appartment (subject, name, route_id) VALUES (?, ?, ?)";
	public static final String INSERT_FLAT_NO = "INSERT INTO milk_flat_no (room_id, app_id) VALUES (?, ?)";
	public static final String INSERT_LOCATION = "INSERT INTO milk_location (subject, name) VALUES (?, ?)";
	public static final String INSERT_MILK_PACKETS = "INSERT INTO milk_packats (subject, milkid, cost) VALUES (?, ?, ?)";
	public static final String INSERT_ROOM_MILK = "INSERT INTO milk_room (room_id, milk_id, quantity) VALUES (?, ?, ?)";
	public static final String INSERT_ROUTE = "INSERT INTO milk_route (subject, route_id) VALUES (?, ?)";
	
	//Select Qureries
	public static final String GET_APPARTMENTS = "SELECT subject, name FROM milk_appartment";
	public static final String GET_FALT_NOS_IN_APP = "SELECT ma.name, mfn.room_id, mr.route_id, mp.milkid, mp.cost, mroom.quantity FROM milk_appartment ma INNER JOIN milk_flat_no mfn ON ma.id = mfn.app_id INNER JOIN milk_route mr ON ma.route_id = mr.id INNER JOIN milk_room mroom ON mroom.room_id = mfn.id INNER JOIN milk_packats mp ON mp.id = mroom.milk_id";
	public static final String GET_LOCATIONS = "select subject, name FROM milk_location";
	public static final String GET_ROUTES = "SELECT subject, route_id FROM milk_route";
	public static final String GET_MILK_PACKETS = "SELECT subject, milkid, cost FROM milk_packats";
	
}
