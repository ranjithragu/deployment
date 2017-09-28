package com.quiz.QuizApp.controller;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.QuizApp.model.Details;
import com.quiz.QuizApp.model.ResponseWrapper;
import com.quiz.QuizApp.model.User;
import com.quiz.QuizApp.service.DetailsService;
import com.quiz.QuizApp.service.UserService;

@RestController
@RequestMapping(value = "/details")
public class DetailsController {

	private Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private DetailsService detailsService;
	
	@RequestMapping(value = "/insertDetails", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Details insertDetails(@RequestBody Details details) {

		return detailsService.insertDetails(details);
	}

	@RequestMapping(value = "/listByUserId", method = RequestMethod.GET)
	@ResponseBody
	public List<Details> findAllQuestions(Details details) {
		List<Details> list = detailsService.findAllQuestions();
		// System.out.println(list.size());
		/*
		 * Iterator<Details> i = list.iterator(); while (i.hasNext()) { Details
		 * name = i.next(); Details name1 = list.get(28); //System.out.println(
		 * "at controller===============>"+name1.getQuestions()); }
		 */
		return list;

	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Details> findAllQuestionsToAdmin(Details details) {
		List<Details> list = detailsService.findAllQuestionsToAdmin();

		return list;

	}

}
