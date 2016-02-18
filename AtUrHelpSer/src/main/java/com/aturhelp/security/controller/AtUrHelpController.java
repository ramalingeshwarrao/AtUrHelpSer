package com.aturhelp.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aturhelp.constants.Constants;

@Controller
public class AtUrHelpController {

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		return model;

	}
	
	@RequestMapping(value = "/provideradmin**", method = RequestMethod.GET)
	public ModelAndView providerAdminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("provider-main-test");

		return model;

	}

	//Spring Security see this :
	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject(Constants.ERROR, Constants.INVALID_USERNAME_PASSWORD);
		}

		if (logout != null) {
			model.addObject(Constants.MSG, Constants.LOGOUT_SUCCESS);
		}
		model.setViewName("login-admin");

		return model;

	}
	
	@RequestMapping(value = "/providerlogin", method = RequestMethod.GET)
	public ModelAndView providerLogin(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject(Constants.ERROR, Constants.INVALID_USERNAME_PASSWORD);
		}

		if (logout != null) {
			model.addObject(Constants.MSG, Constants.LOGOUT_SUCCESS);
		}
		model.setViewName("login-provider");

		return model;

	}
	

}
