package com.aturhelp.handler;

public class InitiateGCMEvent extends AbstractEventObject {
	
	private String requestId;
	private String notificationType;
	

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

}
