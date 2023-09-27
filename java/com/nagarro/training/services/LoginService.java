package com.nagarro.training.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.dao.UserDao;
import com.nagarro.training.models.User;

public class LoginService {
	public String verifyUser(HttpServletRequest request, HttpServletResponse response) {

		// Get Username and password from the form post request
		String username = request.getParameter(Constants.USERNAME_FIELD);
		String password = request.getParameter(Constants.PASSWORD_FIELD);

		// call getUser() method from UserDao class
		UserDao userDao = new UserDao();
		User user = userDao.getUser(username);

		// Check if the user object is null
		if (user == null) {
			// Return that the user does not exists in the database
			return "{\"" + Constants.USERNAME_ERROR_FIELD + "\":\"" + Constants.USERNAME_DOES_NOT_EXISTS + "\"}";
		} else if (!user.getPassword().equals(password)) {
			// Check if the username matches to password
			// else return incorrect password
			return "{\"" + Constants.PASSWORD_ERROR_FIELD + "\":\"" + Constants.INCORRECT_PASSWORD + "\"}";
		}
		// if it is a valid user save user to session
		request.getSession().setAttribute(Constants.USER_SESSION_ATTRIBUTE, user);
		// send user to the products jsp page
		return "";
	}

}
