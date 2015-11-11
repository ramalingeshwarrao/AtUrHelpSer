package com.aturhelp.handler;

public abstract class AbstractEventObject{

	public  enum HandlerMode {
		SYNC,ASYNC;
	}

    protected Object eventSource;

    public void setEventSource(Object eventSource){
        this.eventSource=eventSource;
    }

    public Object getEventSource() {
        return eventSource;
    }

	public void fireEvent(HandlerMode mode) throws Exception {
		EventHandler.getInstance().handleEvent(this, mode);
	}
}
