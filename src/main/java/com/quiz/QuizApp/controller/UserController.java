package com.quiz.QuizApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.apache.h.httpclient.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.QuizApp.model.Details;
//import com.rb.reimbursement.model.ResponseWrapper;
import com.quiz.QuizApp.model.ResponseWrapper;
import com.quiz.QuizApp.model.User;
import com.quiz.QuizApp.service.UserService;
/*import com.rb.reimbursement.serviceImpl.Loginserviceimpl;
import com.rb.reimbursement.serviceImpl.UserserviceImpl;*/

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	private Logger LOG = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseWrapper createUser(@RequestBody User user) {
		ResponseWrapper response = new ResponseWrapper();
		try {
			user = userService.createUser(user);
			if (user == null) {
				response.setResponseError("User not created successfully,plz try once again!");
				return response;
			}

			response.setResult(user);
			response.setResponseSuccess("You have create a user successfully!");

			return response;

		} catch (Exception e) {
			LOG.error("Error while creating user:", e);
			response.setResponseError("Internal service error occured while Creating user, please try again!");
			return response;
		}

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Object doLogin(@RequestBody User user) {
		ResponseWrapper wrap = new ResponseWrapper();
		String userName = user.getUserName();
		String userPassword = user.getPassword();
		 user.getUserName();
		 user.getPassword();
		 user.getRole();
		if (user == null || userName == null || userPassword == null) {
			wrap.setResponseError("UserName and Password Shouldnt be null");
			wrap.setResponseInfo("UserName and Password Shouldnt be null");
			System.out.println(user.getPassword());
			return wrap;
		}
		try {
			User todologin = userService.doLogin(user);
			 todologin.getId();			
			if ((userName.equals(todologin.getUserName()))
					&& (userPassword.equals(todologin.getPassword()))) {
				System.out.println("todologin role" +todologin.getRole());
				System.out.println("todologin" + todologin.getUserName());
				System.out.println("todologin" + todologin.getPassword());
				wrap.setResponseSuccess("You have logged in successfully!");
				wrap.setResult(todologin);
			}
		} catch (Exception e) {
			wrap.setResponseError(" Invalid UserName or Password ");
			System.out.println("localized" + e.getLocalizedMessage());
			System.out.println("Some Error" + e.getMessage());
			return wrap;
		}
		return wrap;

	}
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(value = "/updateScore", method = RequestMethod.PUT)
	public int updateStudent(@RequestParam("userId") Long userId,@RequestParam("score") String score){
		User user = new User();
		user.setScore(score);
		System.err.println("score=================>"+user.getScore());
		int sa = userService.updateScore(user,userId);
	 System.err.println(user.toString());
	 return sa;
	}
	
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public List<User> listByUserId(@RequestParam("userId") Long userId){
		User user = new User();
		List<User> sa = userService.userList(userId);
	
	 return sa;
	}
	
	@RequestMapping(value = "/userlistByFilter", method = RequestMethod.GET)
	public List<User> listByFilter(@RequestParam("college") String college, @RequestParam("department") String department){
		List<User> sa = userService.userListByFilter(college,department);
	
	 return sa;
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findAllUsersToAdmin(User user){
		List<User> list = userService.findAllUsersToAdmin();
		
		return list;
	
	}

}

