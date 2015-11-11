package com.aturhelp.listener;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aturhelp.constants.Constants;
import com.aturhelp.gcm.GCMBraoadCast;
import com.aturhelp.handler.AbstractEventListener;
import com.aturhelp.handler.AbstractEventObject;
import com.aturhelp.handler.InitiateGCMEvent;


public class AtUrHelpGCMListener extends AbstractEventListener{
	
	final static Logger LOG = Logger.getLogger(AtUrHelpGCMListener.class);

	@Override
	public void consume(AbstractEventObject eventObject) throws Exception {


		InitiateGCMEvent event = (InitiateGCMEvent) eventObject;
		String requestId = event.getRequestId();
		String notificationType = event.getNotificationType();
		List<String> requestIdList = new ArrayList<String>(1);
		requestIdList.add(requestId);
		GCMBraoadCast braodCast = new GCMBraoadCast(Constants.SERVER_KEY, requestIdList, notificationType, Constants.ATURHELP);
		LOG.debug("Before sending gcm message");
		braodCast.braodCastMessage();	
		LOG.debug("After sending gcm message");
		
	}

}
