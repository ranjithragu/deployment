package com.quiz.QuizApp.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.QuizApp.dao.DetailsDao;
import com.quiz.QuizApp.dao.UserDao;
import com.quiz.QuizApp.model.Details;
import com.quiz.QuizApp.model.User;

@Service
public class DetailsService {
	@Autowired
	private DetailsDao detailsDao;
	
	public Details insertDetails(Details details) {
		
		return detailsDao.insertDetails(details);

	
	}
	
	public List<Details> findAllQuestions() {
		List<Details> details=detailsDao.findAllQuestions();
		
		long seed = System.nanoTime();
		Collections.shuffle(details, new Random(seed));
		
		return details;

	
	}
	public List<Details> findAllQuestionsToAdmin() {
		List<Details> details=detailsDao.findAllQuestions();
		
		return details;

	
	}


}
