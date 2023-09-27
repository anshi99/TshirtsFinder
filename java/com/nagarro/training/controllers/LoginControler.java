package com.nagarro.training.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.training.services.LoginService;

@Controller
public class LoginControler {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("login");

		return mv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String checkLogin(HttpServletRequest request, HttpServletResponse response) {
		LoginService loginService = new LoginService();
		String result = loginService.verifyUser(request, response);

		response.setContentType("application/json");

		return result;
	}
}
