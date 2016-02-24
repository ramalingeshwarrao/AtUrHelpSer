package com.aturhelp.quartz.jobdetail;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.aturhelp.quartz.task.MilkTask;

public class MilkJobDetail extends QuartzJobBean{

	private MilkTask milkTask;
	
	public void setMilkTask(MilkTask milkTask) {
		this.milkTask = milkTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		
		milkTask.runTask();
	}

}
