package com.aturhelp.listener;

import org.apache.log4j.Logger;

import com.aturhelp.common.ManagedBeans;
import com.aturhelp.common.SpringMail;
import com.aturhelp.handler.AbstractEventListener;
import com.aturhelp.handler.AbstractEventObject;
import com.aturhelp.handler.InitiateMailEvent;

public class AtUrHelpMailListener extends AbstractEventListener{
	
	final static Logger LOG = Logger.getLogger(AtUrHelpMailListener.class);
	private SpringMail mail = null;

	
	@Override
	public void consume(AbstractEventObject eventObject) throws Exception {
		mail = ManagedBeans.getInstance().getBean(SpringMail.class);
		InitiateMailEvent event = (InitiateMailEvent) eventObject;
		LOG.debug("Sending mail");
		mail.sendMail(event.getFromMailAddress(),
	    		   event.getToMailAddress(),
	    		   event.getMailSubject(), 
	    		   event.getMailDescription());
		LOG.debug("Send mail");
	}

}
