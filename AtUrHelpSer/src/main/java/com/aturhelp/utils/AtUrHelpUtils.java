package com.aturhelp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aturhelp.common.AdminInfo;
import com.aturhelp.common.Help;
import com.aturhelp.common.UserInfo;

public class AtUrHelpUtils {

	public static final String pattern = "MM/dd/yyyy";
	public static final String pattern_1 = "yyyy-MM-dd";
	public static String generateMailBody(Help help, AdminInfo info,
			UserInfo userInfo) {

		String Hi = "Hi " + info.getName() + ",";
		String newLine = "\n\n";
		String Customer = "The customer " + userInfo.getName()
				+ " have the follwoing concern";
		String body = "Customer Description : " + help.getHelpDes();
		String customerMobileNo = "Customer Mobile No : "
				+ userInfo.getMobileNo();
		String ticketId = "Ticket ID : " + help.getTicketNo() + "";
		String thanksAndRegards = "Thanks & Regards,";
		String regardsName = "AtUrHelp Team";
		String aturhelpSite = "aturhelp.com";

		return Hi + newLine + Customer + newLine + body + newLine
				+ customerMobileNo + newLine + ticketId + newLine
				+ thanksAndRegards + newLine + regardsName + newLine
				+ aturhelpSite;
	}

	public static String generateMailBodyForCustomer(Help help, UserInfo info) {

		String Hi = "Hi " + info.getName() + ",";
		String newLine = "\n\n";
		String subject = "Subject " + help.getHelpDes();
		String des = "Description : " + help.getHelpDes();
		String ticketId = "Ticket ID : " + help.getTicketNo() + "";
		String resolved = "Has been successful resolved";
		String thanksAndRegards = "Thanks & Regards,";
		String regardsName = "AtUrHelp Team";
		String aturhelpSite = "aturhelp.com";

		return Hi + newLine + subject + newLine + des + newLine
				+ ticketId + newLine + resolved + newLine
				+ thanksAndRegards + newLine + regardsName + newLine
				+ aturhelpSite;
	}
	
	/*
	public static Date getUTCDate(String strDate) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date formatedDate = dateFormat.parse(strDate);
		long zoneTime = formatedDate.getTime();
		DateTimeZone jodaTimezone = DateTimeZone.forTimeZone(TimeZone.getTimeZone("UTC"));
		long utcTime = jodaTimezone.convertLocalToUTC(zoneTime, false);
		Date utcDate = new Date(utcTime);
		return utcDate;
	}
	*/
	
	public static String getTodayDate() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
		
	}
	public static Date getDate(String strDate) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date formatedDate = dateFormat.parse(strDate);
		return formatedDate;
	}
	
	public static String getCurrentDate() throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date());
	}
	
	public static int getNoOfDaysInRange(String fromDate, String toDate, String pat) {
			DateTimeFormatter dateStringFormat = null;
			if (StringUtils.isNotBlank(pat)) {
				dateStringFormat = DateTimeFormat.forPattern(pat);	
			} else {
				dateStringFormat = DateTimeFormat.forPattern(pattern);
			}
			DateTime fromTime = dateStringFormat.parseDateTime(fromDate);
			DateTime toTime = dateStringFormat.parseDateTime(toDate);
			int days = Days.daysBetween(new LocalDate(fromTime), new LocalDate(toTime)).getDays();
			return days+1;
	}
	
	public static int getMonFromGivenDate(String date, String pat) throws Exception{
		SimpleDateFormat dateFormat = null;
		if (StringUtils.isNotBlank(pat)) {
			dateFormat = new SimpleDateFormat(pat);	
		} else {
			dateFormat = new SimpleDateFormat(pattern);			
		}
	    Date dateObj = dateFormat.parse(date);
	    Calendar calObj = Calendar.getInstance();
	    calObj.setTime(dateObj);
	    int month = calObj.get(Calendar.MONTH)+1;
	    return month;
	}
	
	public static boolean compareDates(String sdate1, String sdate2, String pattern) throws Exception {
		SimpleDateFormat fDate = new SimpleDateFormat(pattern);
		Date date1 = fDate.parse(sdate1);
		Date date2 = fDate.parse(sdate2);
		if (date1.before(date2)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String convertDateFormat(String date, String actualFormat, String newFormat) throws Exception {
		SimpleDateFormat actualDateFormat = new SimpleDateFormat(actualFormat);
		SimpleDateFormat newDateFormat = new SimpleDateFormat(newFormat);
		Date actualDate = actualDateFormat.parse(date);
		String formattedDate = newDateFormat.format(actualDate);
		return formattedDate;
	}
	
	public static String getLoggedUserName() {
		Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
		String providerName = authObj.getName();
		return providerName;
	}
	
	public static void main(String[] args) {
		System.out.println("test");
		int days = getNoOfDaysInRange("12/29/2015", "01/02/2016", null);
		System.out.println(days);
		try {
			int month = getMonFromGivenDate("12/19/2015", null);
			System.out.println(month);
		} catch (Exception e) {
			
		}
	}
	
}
