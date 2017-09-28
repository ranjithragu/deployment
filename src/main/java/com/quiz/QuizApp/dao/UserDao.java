package com.quiz.QuizApp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quiz.QuizApp.model.Details;
import com.quiz.QuizApp.model.User;
import com.quiz.QuizApp.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userRepository;
	
	public User findByUserName(String userName) { 

		return userRepository.findByUserName(userName);
	}
	@Transactional
	public User saveUser(User user) {

		return userRepository.save(user);

	}
	public User doLogin(User user) { 

		System.out.println("in dao"+user.getUserName());
		System.out.println("in dao password"+user.getPassword().toUpperCase()); 
	    return userRepository.doLogin(user.getUserName(),user.getPassword()); 
		
	}
	
	public int resetPassword(String oldpassword, String password, Long id) { 

		return userRepository.resetPassword(oldpassword,password,id);
		
	}
	public int updateScore(User user, Long userId) { 
		String score =user.getScore();
		System.err.println("score==============>"+score);
		return userRepository.updateScore(score,userId);
		
	}
	
	public List<User> userList(Long userId) { 
		
		return userRepository.userList(userId);
		
	}
	
public List<User> userListByFilter(String college,String department) { 
	List<User> filtered =	userRepository.userListByFilter(college,department);
	System.err.println("filtered=========>"+filtered.size());
		return filtered;
		
	}
	
	
	
	public List<User> findAllUsersToAdmin() {
		List<User> list = userRepository.findAll();
		System.err.println("list=============>"+list);
		return list;

	}
	


}
