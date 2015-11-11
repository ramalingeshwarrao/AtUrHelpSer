package com.aturhelp.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ManagedBeans implements ApplicationContextAware {
	
	private static final ManagedBeans MANAGED_BEANS = new ManagedBeans();
	
	private ApplicationContext applicationContext;
	
	private ManagedBeans() {}
	
	public static ManagedBeans getInstance() {
		return MANAGED_BEANS;
	}

	public Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}
	
	public <T> T getBean(String beanId, Class<T> beanClass) {
		return applicationContext.getBean(beanId, beanClass);
	}
	
	public <T> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;		
	}

}
