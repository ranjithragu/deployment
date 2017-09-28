package com.quiz.QuizApp.dao;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.QuizApp.model.Details;
import com.quiz.QuizApp.model.User;
import com.quiz.QuizApp.repository.DetailsRepository;
import com.quiz.QuizApp.repository.UserRepository;

@Repository
public class DetailsDao {
	@Autowired
	private DetailsRepository detailsRepository;
	
	@Transactional
	public Details insertDetails(Details details) {
		
		return detailsRepository.save(details);
		   		
		  

	}
	public List<Details> findAllQuestions() {
		List<Details> details = detailsRepository.findAll();
		return details;

	}
	


}
