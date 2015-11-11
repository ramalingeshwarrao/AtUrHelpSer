package com.aturhelp.handler;

public class InitiateMailEvent extends AbstractEventObject {

	
	private String fromMailAddress;
	private String toMailAddress;
	private String mailSubject;
	private String mailDescription;
	
	public String getFromMailAddress() {
		return fromMailAddress;
	}

	public void setFromMailAddress(String fromMailAddress) {
		this.fromMailAddress = fromMailAddress;
	}

	public String getToMailAddress() {
		return toMailAddress;
	}

	public void setToMailAddress(String toMailAddress) {
		this.toMailAddress = toMailAddress;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailDescription() {
		return mailDescription;
	}

	public void setMailDescription(String mailDescription) {
		this.mailDescription = mailDescription;
	}
}
