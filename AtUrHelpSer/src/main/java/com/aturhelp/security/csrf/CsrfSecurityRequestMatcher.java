package com.aturhelp.security.csrf;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher{

	private OrRequestMatcher unprotectedRequestMatcher;
	private Pattern allowedMethods = Pattern.compile("^(POST|GET|HEAD|TRACE|OPTIONS)$");
	
	@Override
	public boolean matches(HttpServletRequest request) {
		if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }
	    return !unprotectedRequestMatcher.matches(request);
	}

	public OrRequestMatcher getUnprotectedRequestMatcher() {
		return unprotectedRequestMatcher;
	}

	public void setUnprotectedRequestMatcher(
			OrRequestMatcher unprotectedRequestMatcher) {
		this.unprotectedRequestMatcher = unprotectedRequestMatcher;
	}
 	 
}
