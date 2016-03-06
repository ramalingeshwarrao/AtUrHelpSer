package com.aturhelp.quartz.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.aturhelp.services.MilkService;

public class MilkTaskTest {
	
	final static Logger LOG = Logger.getLogger(MilkTaskTest.class);
	
	@Autowired(required=true)
	private MilkService milkService;

	public void runTask() {
		String timeStampStarted = new SimpleDateFormat("MM/dd/YYY HH-mm-ss")
				.format(Calendar.getInstance().getTime());
		System.out.println("Timer started timestamp " + timeStampStarted);
		LOG.error("Not an error Timer started");
		for (int i = 1; i <= 29; i++) {
			milkService.testDailyTimer("02/"+i+"/2016");
		}
		String timeStampCompleted = new SimpleDateFormat("MM/dd/YYY HH-mm-ss")
				.format(Calendar.getInstance().getTime());
		System.out.println("Timer completed timestamp " + timeStampCompleted);
		LOG.error("Timer completed");
	}
}
