package com.quiz.QuizApp.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quiz.QuizApp.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	@Query("Select s from  User s WHERE s.userName=:userName")
	public User findByUserName(@Param("userName")String userName);   
	
	@Query("Select s from  User s WHERE s.userName=:userName AND s.password=:password")
	public User doLogin(@Param("userName") String userName,
			@Param("password") String password);
	
	@org.springframework.data.jpa.repository.Modifying
	@Transactional
	@Query("Update User s SET s.password=:password WHERE s.id=:id AND s.password=:oldpassword")
	public int resetPassword(@Param("oldpassword")String oldpassword,@Param("password") String password,@Param("id") Long id);
	
	@org.springframework.data.jpa.repository.Modifying
	@Transactional
	@Query("Update User s SET s.score=:score WHERE s.id=:id")
	public int updateScore(@Param("score")String score,@Param("id") Long id);
	
	/*@org.springframework.data.jpa.repository.Modifying
	@Transactional*/
	@Query("Select e FROM User e WHERE e.id = :id")
	public List<User> userList(@Param("id") Long id);
	
	@Query("Select e FROM User e WHERE e.college = :college AND department = :department")
	public List<User> userListByFilter(@Param("college") String college,@Param("department") String department);
	
	@Modifying
	@Transactional
	@Query("Delete User s where s.id=:id")
	public int deleteById(@Param("id")Long id);

	
	@Query("Select s from  User s WHERE s.userName=:userName ")
	public User findByUsername_security(@Param("userName") String userName);

	public User findById(Long id);
	
	@Query("Select s from  User s WHERE s.password=:password")
	public User findByPassword(@Param("password")String password);


}
