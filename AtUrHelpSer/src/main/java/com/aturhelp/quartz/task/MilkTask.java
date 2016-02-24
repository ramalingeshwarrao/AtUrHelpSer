package com.aturhelp.quartz.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aturhelp.services.MilkService;


public class MilkTask {
	
	final static Logger LOG = Logger.getLogger(MilkTask.class);
	
	@Autowired(required=true)
	private MilkService milkService;

	public void runTask() {
		String timeStampStarted = new SimpleDateFormat("MM/dd/YYY HH-mm-ss")
				.format(Calendar.getInstance().getTime());
		System.out.println("Timer started timestamp " + timeStampStarted);
		LOG.error("Not an error Timer started");
		milkService.dailyTimer();
		String timeStampCompleted = new SimpleDateFormat("MM/dd/YYY HH-mm-ss")
				.format(Calendar.getInstance().getTime());
		System.out.println("Timer completed timestamp " + timeStampCompleted);
		LOG.error("Timer completed");
	}
}
