package com.aturhelp.configuration;

import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.client.apache4.ApacheHttpClient4;
import com.sun.jersey.client.apache4.config.ApacheHttpClient4Config;
import com.sun.jersey.client.apache4.config.DefaultApacheHttpClient4Config;

public class Configuration {

	final static Logger LOG = Logger.getLogger(Configuration.class);
	
	public static final Client PROXY_AWARE_REST_CLIENT = initRESTClient();
	
	public static final Client NON_PROXY_AWARE_REST_CLIENT = initNonProxyRESTClient();
		
	private static Client initRESTClient() {
		
		DefaultApacheHttpClient4Config config = new DefaultApacheHttpClient4Config();
		
		PoolingClientConnectionManager connMngr = new PoolingClientConnectionManager();
		connMngr.setMaxTotal(30);
		
		config.getProperties().put(ApacheHttpClient4Config.PROPERTY_CONNECTION_MANAGER, connMngr);
		
		try {
			config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 300000);
			config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, 1800000);
		} catch (Exception e) {
			LOG.error("Invalid values configured for Connection/Read timeout for creating proxy aware rest client.", e);
			// DO NOTHING! FALL BACKS TO DEFAULT JERSEY CLIENT TIMEOUTS
		}
		return ApacheHttpClient4.create(config);
		
	}
	
	private static Client initNonProxyRESTClient() {
		DefaultApacheHttpClient4Config config = new DefaultApacheHttpClient4Config();
		
		PoolingClientConnectionManager connMngr = new PoolingClientConnectionManager();
		connMngr.setMaxTotal(30);
		
		config.getProperties().put(ApacheHttpClient4Config.PROPERTY_CONNECTION_MANAGER, connMngr);
		
		try {
			config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT,300000);
			config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, 1800000);
		} catch (Exception e) {
			LOG.error("Invalid values configured for Connection/Read timeout for creating non-proxy aware rest client.", e);
			// DO NOTHING! FALL BACKS TO DEFAULT JERSEY CLIENT TIMEOUTS
		}

		return ApacheHttpClient4.create(config);
	}
}
