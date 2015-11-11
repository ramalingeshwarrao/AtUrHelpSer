package com.aturhelp.gcm;

import java.util.List;


import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

public class GCMBraoadCast {

	private String sender_id;
	private List<String> requestIdList = null;
	private String message;
	private String domainName; 

	public GCMBraoadCast(String sender_id, List<String> requestIdList, String message, String domainName) {
		this.sender_id = sender_id;
		this.requestIdList = requestIdList;
		this.message = message;
		this.domainName = domainName;
	}

	public void braodCastMessage() throws Exception {

		// This does the transmission of a Message to the Google Cloud Messaging
		// service
		Sender sender = new Sender(sender_id);

		// This Message object will hold the data that is being transmitted
		// to the Android client devices.
		Message msgBuilder = new Message.Builder()

		// If multiple messages are sent using the same .collapseKey()
		// the android target device, if it was offline during earlier
		// message
		// transmissions, will only receive the latest message for that
		// key when
		// it goes back on-line.
		.collapseKey(domainName).timeToLive(30).delayWhileIdle(true)
		.addData("message", message).build();

		// use this for multicast messages. The second parameter
		// of sender.send() will need to be an array of register ids.
		MulticastResult result = sender.send(msgBuilder, requestIdList, 1);

		if (result.getResults() != null) {
			int canonicalRegId = result.getCanonicalIds();
			if (canonicalRegId != 0) {

			}
		} else {
			int error = result.getFailure();
			throw new Exception("Fail to send MSG" + error);
		}

	}

}
