package com.quiz.QuizApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quiz.QuizApp.model.Details;

public interface DetailsRepository extends JpaRepository<Details, String> {

}
