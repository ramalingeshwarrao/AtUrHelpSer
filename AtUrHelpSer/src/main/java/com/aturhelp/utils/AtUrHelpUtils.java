package com.aturhelp.utils;

import com.aturhelp.common.AdminInfo;
import com.aturhelp.common.Help;
import com.aturhelp.common.UserInfo;

public class AtUrHelpUtils {

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
}
