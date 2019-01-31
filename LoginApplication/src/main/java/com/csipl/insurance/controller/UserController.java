package com.csipl.insurance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csipl.insurance.model.UserRegistration;
import com.csipl.insurance.model.Users;
import com.csipl.insurance.service.UserRegistrationService;
import com.csipl.insurance.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {
	 
	@Autowired
	UserService userService;

	@Autowired
	UserRegistrationService userRegistrationService;
	//@RequestParam("emailId") String email ,@RequestParam("password") String password
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserRegistration loginApp(@RequestBody UserRegistration login, HttpServletRequest req) {
		// logger.info("UserController.loginApp(--------------------------------------------------)");
		String email = login.getEmailId().trim();
		String password = login.getPassword();

		System.out.println("Email   "+email);
		System.out.println("password   "+password);
		boolean isActive = true; 
		
		if (email != null || email == "" && password != null || password == "") {
			UserRegistration users = userService.findUser(email, password, isActive);
			System.out.println("User Data=========" + users);
			if (users != null && users.getEmailId().equals(email) && users.getPassword().equals(password)) {
				return users;
			}
		}

		return login;
	}

	// @RequestMapping(value = "/login", method = RequestMethod.POST)
	// public @ResponseBody Users loginApp(@RequestBody Users user,
	// HttpServletRequest req) {
	// //logger.info("UserController.loginApp(--------------------------------------------------)");
	// String username = user.getUsername().trim();
	// String password = user.getPassword().trim();
	//
	// if (user.getUsername() != null || user.getUsername() == "" &&
	// user.getPassword() != null
	// || user.getPassword() == "") {
	// Users users = userService.findUser(username, password);
	// System.out.println("User Data=========" + users);
	// if (users != null && users.getUsername().equals(username) &&
	// users.getPassword().equals(password)) {
	// return users;
	// }
	// }
	//
	// return user;
	// }
}