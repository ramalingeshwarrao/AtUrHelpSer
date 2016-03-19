package com.aturhelp.security.csrf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CSRFIgnoreURLSFactoryBean implements InitializingBean, FactoryBean<OrRequestMatcher>{

	
	private List<String> csrfIgnoreUrlsList;
	
	private List<RequestMatcher> requestMatchers = new ArrayList<RequestMatcher>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		for (String url : csrfIgnoreUrlsList) {
			requestMatchers.add(new AntPathRequestMatcher(url));
		}
	}
	
	@Override
	public OrRequestMatcher getObject() throws Exception {
		return new OrRequestMatcher(requestMatchers);
	}
	
	public java.lang.Class<OrRequestMatcher> getObjectType() {
		return OrRequestMatcher.class;
	};
	
	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setCsrfIgnoreUrlsList(List<String> csrfIgnoreUrlsList) {
		this.csrfIgnoreUrlsList = csrfIgnoreUrlsList;
	}


}
