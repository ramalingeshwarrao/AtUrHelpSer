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
	public static final String INSERT_APPARTMENT = "INSERT INTO milk_appartment (subject, name, route_id, provider_id) VALUES (?, ?, ?, ?)";
	public static final String INSERT_FLAT_NO = "INSERT INTO milk_flat_no (room_id, app_id, provider_id, is_active) VALUES (?, ?, ?, ?)";
	public static final String INSERT_LOCATION = "INSERT INTO milk_location (subject, name, provider_id) VALUES (?, ?, ?)";
	public static final String INSERT_MILK_PACKETS = "INSERT INTO milk_packats (subject, milkid, cost, provider_id) VALUES (?, ?, ?, ?)";
	public static final String INSERT_ROOM_MILK = "INSERT INTO milk_room (room_id, milk_id, quantity, provider_id, is_alternative) VALUES (?, ?, ?, ?, ?)";
	public static final String INSERT_ROUTE = "INSERT INTO milk_route (subject, route_id, provider_id) VALUES (?, ?, ?)";
	public static final String INSERT_CATEGORY = "INSERT INTO milk_category (name) VALUES (?)";
	public static final String INSERT_NO_MILK = "INSERT INTO milk_nomilk (fromdate, rid, isUpdated, todate, provider_id) values (?, ?, ?, ?, ?)";
	public static final String INSERT_NO_MILK_NO_TODATE = "INSERT INTO milk_nomilk (fromdate, rid, isUpdated, provider_id) values (?, ?, ?, ?)";
	
	//Select Qureries
	public static final String GET_APPARTMENTS = "SELECT subject, name, id FROM milk_appartment where provider_id=?";
	public static final String GET_APPARTMENTS_BY_ID = "SELECT subject, name, id FROM milk_appartment where route_id=? AND provider_id=?";
	public static final String GET_FALT_NOS_IN_APP = "SELECT ma.subject as appsubject, ma.name, mfn.room_id, mr.route_id, mp.milkid, mp.cost, mroom.quantity, mfn.comments FROM milk_appartment ma INNER JOIN milk_flat_no mfn ON ma.id = mfn.app_id AND mfn.is_active = 1 INNER JOIN milk_route mr ON ma.route_id = mr.id INNER JOIN milk_room mroom ON mroom.room_id = mfn.id INNER JOIN milk_packats mp ON mp.id = mroom.milk_id AND mfn.provider_id=? limit ?,?";
	public static final String GET_LOCATIONS = "select subject, name FROM milk_location  where provider_id=?";
	public static final String GET_ROUTES = "SELECT id, subject, route_id FROM milk_route where provider_id=?";
	public static final String GET_CATEGORIES = "SELECT name from milk_category";
	public static final String GET_MILK_PACKETS = "SELECT id, subject, milkid, cost FROM milk_packats where provider_id=?";
	public static final String GET_FLAT_NO = "SELECT id, room_id, app_id FROM milk_flat_no where provider_id=? AND is_active=1";
	public static final String GET_FLAT_N0_BY_AP_ID = "SELECT id, room_id, app_id FROM milk_flat_no WHERE app_id=? AND provider_id=? AND is_active=1";
	public static final String GET_COUNT_MILK_DATA = "SELECT count(1) from milk_flat_no mfn inner join milk_room mr on mfn.id = mr.room_id where mfn.provider_id=? and is_active=1"; 
	public static final String GET_STATUS_ROOM_MILK = "SELECT isUpdated FROM milk_nomilk WHERE rid=? AND provider_id=?";
	public static final String GET_DAY_MILK_BY_ROUTE_ID = "SELECT ma.subject as appsubject, ma.name, mfn.room_id, mr.route_id, mp.milkid, mp.cost, mroom.quantity FROM milk_appartment ma INNER JOIN milk_flat_no mfn ON ma.id = mfn.app_id AND mfn.is_active = 1 INNER JOIN milk_route mr ON ma.route_id = mr.id INNER JOIN milk_room mroom ON mroom.room_id = mfn.id INNER JOIN milk_packats mp ON mp.id = mroom.milk_id WHERE mfn.id NOT IN (select rid from milk_nomilk where fromdate <= ? AND isUpdated=0  OR fromdate <= ? AND todate >= ? AND provider_id=?) AND ma.route_id=? AND ma.provider_id=? order by ma.name, mfn.room_id";
	
	public static final String GET_TIMER_DAY_MILK_BY_ROUTE_ID = "SELECT ma.subject as appsubject, ma.name, mfn.room_id, mr.route_id, mp.milkid, mp.cost, mroom.quantity, mp.id as pid, mfn.id as mfnid  FROM milk_timer mt INNER JOIN milk_flat_no mfn ON mfn.id = mt.rid INNER JOIN milk_packats mp ON mp.id = mt.mid INNER JOIN milk_appartment ma ON ma.id = mfn.app_id INNER JOIN milk_route mr ON mr.id = ma.route_id INNER JOIN milk_room mroom ON mroom.room_id = mfn.id AND mroom.milk_id = mp.id WHERE mt.milkdate= ? AND ma.route_id = ? AND mt.provider_id = ? order by ma.priority";
	
	public static final String GET_DAY_MILK = "SELECT ma.subject as appsubject, ma.name, mfn.id as room_id, mr.route_id, mp.id as milkid, mp.cost, mroom.quantity, mroom.is_alternative, mroom.provider_id FROM milk_appartment ma INNER JOIN milk_flat_no mfn ON ma.id = mfn.app_id AND mfn.is_active = 1 INNER JOIN milk_route mr ON ma.route_id = mr.id INNER JOIN milk_room mroom ON mroom.room_id = mfn.id INNER JOIN milk_packats mp ON mp.id = mroom.milk_id WHERE mfn.id NOT IN (select rid from milk_nomilk where fromdate <= ? AND isUpdated=0  OR fromdate <= ? AND todate >= ? AND provider_id=?) AND ma.provider_id=? order by ma.name, mfn.room_id";
	public static final String GET_NO_MILK_BY_ROOM_ID_APP_ID = "select mn.id, ma.name, mfn.room_id, mn.fromdate from milk_nomilk mn INNER JOIN milk_flat_no mfn ON mn.rid = mfn.id INNER JOIN milk_appartment ma ON ma.id = mfn.app_id WHERE isUpdated=? AND ma.id=? AND mfn.id=? AND mfn.provider_id=?";
	public static final String UPDATE_NO_MILK_TO_GET_MILK = "update milk_nomilk set isUpdated=?,todate=? WHERE rid=? AND isUpdated=0 AND provider_id=?";
	public static final String NO_MILK_FIRST_CASE = "SELECT count(1) FROM milk_nomilk WHERE rid=? AND fromdate <=? AND isUpdated=? AND provider_id=?";
	public static final String NO_MILK_VALIDATE_FD_GD_GIVEN_TD = "select count(1) FROM milk_nomilk WHERE rid=? AND fromdate >=? AND provider_id=?";
	public static final String VALIDATE_DATE_IN_RANGE = "SELECT count(1) FROM milk_nomilk where rid = ?  AND fromdate <= ? AND todate >= ? AND provider_id=?";
	public static final String VALIDATE_RANGE_IN_RANGE = "SELECT count(1) FROM milk_nomilk WHERE rid=? AND fromdate >= ? AND todate <= ? AND isUpdated=1 AND provider_id=?";
	public static final String NO_MILK_FIRST_CASE_IF_TO_DATE_NULL = "SELECT count(1) FROM milk_nomilk WHERE rid=? AND fromdate >=? AND provider_id=?";
	public static final String NO_MILK_SECOND_CASE = "SELECT count(1) FROM milk_nomilk WHERE rid=? AND fromdate <=? AND todate >= ? AND isUpdated=? AND provider_id=?";
	public static final String GET_ALL_NO_MILK_BY_ROOM_ID_APP_ID = "select ma.name, mfn.room_id, mn.fromdate, mn.todate from milk_nomilk mn INNER JOIN milk_flat_no mfn ON mn.rid = mfn.id INNER JOIN milk_appartment ma ON ma.id = mfn.app_id WHERE ma.id=? AND mfn.id=? AND mfn.provider_id=?";
	public static final String GET_MILK_COST_BY_APP_ID = "select mfn.id, mfn.room_id, sum(cost) as cost, count(1) as quantity from milk_flat_no mfn inner join milk_room mr on mfn.id = mr.room_id inner join milk_packats mp on mp.id = mr.milk_id inner join milk_appartment ma on ma.id = mfn.app_id where app_id=? AND mfn.provider_id=? group by mfn.room_id order by mfn.room_id";
	public static final String GET_NO_MILK_FOR_COST_BY_RID = "select fromdate, todate from milk_nomilk where fromdate >= ? and fromdate <= ? and rid=? and provider_id=?";
	public static final String GET_NO_MILK_FOR_COST_BY_RID_NULL = "select * from milk_nomilk where todate is null and rid=? and provider_id=?";
	//public static final String GET_CONSUMED_MILK = "SELECT mp.milkid, ROUND(SUM(quantity * lts), 2) as liters FROM milk_appartment ma INNER JOIN milk_flat_no mfn ON ma.id = mfn.app_id INNER JOIN milk_route mr ON ma.route_id = mr.id INNER JOIN milk_room mroom ON mroom.room_id = mfn.id INNER JOIN milk_packats mp ON mp.id = mroom.milk_id WHERE mfn.id NOT IN (select rid from milk_nomilk where fromdate <= ? AND isUpdated=0  OR fromdate <= ? AND todate >= ? AND provider_id=?)  AND ma.provider_id=? group by mp.milkid order by mp.milkid, category";
	public static final String GET_CONSUMED_MILK = "select mp.milkid, ROUND(SUM(mt.quantity * lts), 2) as liters from milk_timer mt inner join milk_flat_no mfn on mt.rid = mfn.id inner join milk_packats mp on mp.id = mt.mid where mt.provider_id = ? and milkdate = ? group by mp.milkid order by mp.milkid, category";
	//public static final String GET_CONSUMED_MILK_BY_ROUTE_ID = "SELECT mp.milkid ,mr.route_id, ROUND(SUM(quantity * lts), 2) as liters FROM milk_appartment ma INNER JOIN milk_flat_no mfn ON ma.id = mfn.app_id INNER JOIN milk_route mr ON ma.route_id = mr.id INNER JOIN milk_room mroom ON mroom.room_id = mfn.id INNER JOIN milk_packats mp ON mp.id = mroom.milk_id WHERE mfn.id NOT IN (select rid from milk_nomilk where fromdate <= ? AND isUpdated=0  OR fromdate <= ? AND todate >= ? AND provider_id=?)  AND ma.provider_id=? group by mp.milkid, mr.route_id order by mr.route_id, mp.milkid, category";
	public static final String GET_CONSUMED_MILK_BY_ROUTE_ID = "select mp.milkid, mr.route_id, ROUND(SUM(mt.quantity * lts), 2) as liters from milk_timer mt inner join milk_flat_no mfn on mt.rid = mfn.id inner join milk_packats mp on mp.id = mt.mid inner join milk_appartment ma on ma.id = mfn.app_id inner join milk_route mr on mr.id = ma.route_id where mt.provider_id = ? and milkdate = ? group by mp.milkid, mr.route_id order by mr.route_id, mp.milkid, category";
	
	public static final String UPDATE_COMMENT_FOR_FLAT = "UPDATE milk_flat_no set comments = ? WHERE id = ?";
	public static final String GET_COMMENT = "SELECT comments FROM milk_flat_no WHERE id = ?";
	public static final String GET_MILK_BY_ROOM_ID = "select mp.id, mp.subject, mp.milkid, mp.cost, mp.category, mr.quantity from milk_packats mp INNER JOIN milk_room mr ON mp.id = mr.milk_id where mr.room_id = ? AND mp.provider_id = ? AND mr.is_alternative=0";
	public static final String GET_QUANTITY_BY_ROOMID_MILK_ID = "select quantity from milk_room where room_id =? AND milk_id=?";
	public static final String UPDATE_IS_ALTERNATIVE = "UPDATE milk_room set is_alternative = ?, alter_count = ? WHERE room_id = ? AND milk_id = ?";
	public static final String UPDATE_FLAT_IN_ACTIVE = "UPDATE milk_flat_no set is_active = ?, in_active_from = ? where id = ?";
	public static final String INSERT_TIMER_DATA = "INSERT INTO milk_timer (cat, rid, mid, quantity, milkdate, provider_id) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String GET_PROVIDERS = "select distinct provider_id from milk_room";
	
	//milk edit update queries
	public static final String EDIT_TIMER_MID_QTY = "update milk_timer set mid=?, quantity=? where rid=? and mid=? and milkdate=?";
	public static final String EDIT_TIMER_MID = "update milk_timer set mid=? where rid=? and mid=? and milkdate=?";
	public static final String EDIT_TIMER_QTY = "update milk_timer set quantity=? where rid=? and mid=? and milkdate=?";
	public static final String EDIT_M_ROOM_MID_QTY = "update milk_room set milk_id=?, quantity=? where room_id=? and milk_id=?";
	public static final String EDIT_M_ROOM_MID = "update milk_room set milk_id=? where room_id=? and milk_id=?";
	public static final String EDIT_M_ROOM_QTY = "update milk_room set quantity=? where room_id=? and milk_id=?";
	public static final String DELETE_MILK_TIMER_DATA = "delete from milk_timer where rid=? AND  mid=? AND milkdate=? AND provider_id=?";
	public static final String GET_BILL_BY_APP_ID = "select ma.subject , mfn.room_id,  sum(mt.quantity * mp.cost) as finalcost from milk_timer mt inner join milk_flat_no mfn on mt.rid = mfn.id inner join milk_packats mp on mp.id = mt.mid inner join milk_room mr on mr.room_id = mt.rid and mr.milk_id = mt.mid inner join milk_appartment ma on ma.id = mfn.app_id where mt.milkdate between ? and ? and mt.provider_id = ? and ma.id = ? group by ma.subject, mfn.room_id, mt.provider_id";
	public static final String GET_APP_ID_PRIORITY_BY_ROUTE_ID = "select ma.subject as appname, ma.id as appid, ma.priority  from milk_appartment ma where ma.route_id = ?";
	public static final String UPDATE_PRIORITY = "update milk_appartment set priority=? where id=?";
	
}
