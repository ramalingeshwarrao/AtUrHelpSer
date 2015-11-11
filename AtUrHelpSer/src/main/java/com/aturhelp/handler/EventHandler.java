package com.aturhelp.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


public class EventHandler {
	
	private static final EventHandler handler = new EventHandler();
	private transient Map<Class<? extends AbstractEventObject>, List<AbstractEventListener>> eventListenerMap = new HashMap<Class<? extends AbstractEventObject>, List<AbstractEventListener>>();
	final static Logger LOG = Logger.getLogger(EventHandler.class);
	
	private final ExecutorService threadPoolExecutor;
	
	public void shutdown() {
		threadPoolExecutor.shutdownNow();
	}
	
	private EventHandler() {
		int poolSize = 20;
        
		String asyncPoolSize = System.getProperty("ASYNC_POOL_SIZE");
		if(StringUtils.isNotBlank(asyncPoolSize)) {
			try {
				poolSize = Integer.parseInt(asyncPoolSize);
			} catch(Exception e) {
				LOG.error("ASYNC_POOL_SIZE valueDefaults to 20 "+asyncPoolSize, e);
			}
		}
		
		threadPoolExecutor = Executors.newFixedThreadPool(poolSize);
	}
	
	public static EventHandler getInstance() {
		return handler;
	}
	
	public synchronized void registerEventListener(Class<? extends AbstractEventObject> eventClass,Class<? extends AbstractEventListener>... listenerClasses) throws Exception {
		if(eventListenerMap.containsKey(eventClass)){
			for(Class<? extends AbstractEventListener> listenerClass: listenerClasses){
				eventListenerMap.get(eventClass).add((AbstractEventListener) listenerClass.newInstance());
			}
		}else{
			eventListenerMap.put(eventClass, new ArrayList<AbstractEventListener>());
			registerEventListener(eventClass, listenerClasses);
		}		
	}
	
	public synchronized void unregisterEventListener(Class<? extends AbstractEventObject> eventClass,Class<? extends AbstractEventListener>... listenerClasses) throws Exception {
		if(eventListenerMap.containsKey(eventClass)){			
			for(Class<? extends AbstractEventListener> listenerClass: listenerClasses){
				eventListenerMap.get(eventClass).remove((AbstractEventListener) listenerClass.newInstance());
			}
			if(eventListenerMap.get(eventClass).size() == 0){
				eventListenerMap.remove(eventClass);
			}	
		}			
	}
	
	protected void handleEvent(AbstractEventObject eventObject, AbstractEventObject.HandlerMode mode) throws Exception {
		List<AbstractEventListener> listeners = eventListenerMap.get(eventObject.getClass());
		if (listeners != null && listeners.size() > 0) {
			for (AbstractEventListener listener : listeners) {
				if (AbstractEventObject.HandlerMode.SYNC.equals(mode)) {
					listener.consume(eventObject);
				} else if (AbstractEventObject.HandlerMode.ASYNC.equals(mode)) {
					
					Runnable runnableObject = new Runnable() {
						AbstractEventListener localListener;
						AbstractEventObject localEvent;
						
						public void run() {
							
							try {
								localListener.consume(localEvent);
							} catch (Exception e) {
								LOG.error("Threaded exception",e);
							} 
						}
						
						private Runnable init(AbstractEventObject event, AbstractEventListener listener) {
							this.localEvent = event;
							this.localListener = listener;
							return this;
						}
					}.init(eventObject, listener);
					
//					Thread thread = new Thread(runnableObject);
//					thread.start();
					
					threadPoolExecutor.execute(runnableObject);
					
				}
			}
		}
	}
}
