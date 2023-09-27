package com.nagarro.training.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public ModelAndView logoutUser() {
		ModelAndView mv = new ModelAndView("logout");
		return mv;
	}
}
