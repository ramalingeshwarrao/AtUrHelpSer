package com.aturhelp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.aturhelp.configuration.Configuration;
import com.aturhelp.handler.EventHandler;
import com.aturhelp.handler.InitiateGCMEvent;
import com.aturhelp.handler.InitiateMailEvent;
import com.aturhelp.services.impl.HelpServiceImpl;

public class AtUrHelpContextListener implements ServletContextListener{

	final static Logger LOG = Logger.getLogger(HelpServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	private void registerPackagingEvents() throws Exception {
		EventHandler.getInstance().registerEventListener(InitiateGCMEvent.class, AtUrHelpGCMListener.class);
		EventHandler.getInstance().registerEventListener(InitiateMailEvent.class, AtUrHelpMailListener.class);	
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			// destroying the client
			Configuration.PROXY_AWARE_REST_CLIENT.destroy();
			// destroying the non-proxy client
			Configuration.NON_PROXY_AWARE_REST_CLIENT.destroy();
			
			
			EventHandler.getInstance().shutdown();
			
		} catch (Exception e) {
			LOG.error("Exception occured while releasing resources on context destroy", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Scheduler scheduler = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext()).getBean(Scheduler.class);
			if (scheduler != null && !scheduler.isStarted()) {
				if(LOG.isInfoEnabled()) LOG.info("Starting quartz scheduler");
				scheduler.start();
			}
			
			if(LOG.isInfoEnabled()) LOG.info("Registering events");
			registerPackagingEvents();
		} catch (Exception e) {
			LOG.error("Exception while initializing MAM context events", e);
		}
	}

}
