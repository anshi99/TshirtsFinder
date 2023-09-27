package com.nagarro.training.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.nagarro.training.asynctask.CSVFileLoaderTask;
import com.nagarro.training.dao.TShirtDao;
import com.nagarro.training.models.TShirt;

@Controller
public class TShirtFinder {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView getHomepage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("index");

		new Thread(new CSVFileLoaderTask()).start();
		return mv;
	}

	@RequestMapping(value = { "/filter" }, method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String fetchTShirts(HttpServletRequest request, HttpServletResponse response) {
		TShirtDao tShirtDao = new TShirtDao();
		tShirtDao.setFilters(request.getParameter("color"), request.getParameter("size"),
				request.getParameter("gender"), Integer.parseInt(request.getParameter("output_preference")));

		List<TShirt> tShirts = tShirtDao.getTShirtList();

		response.setContentType("application/json");

		return new Gson().toJson(tShirts);
	}
}
