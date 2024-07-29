package com.miniproject.Quizziz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.MCQQuestion;

@Repository
public interface MCQQuestionRepository extends JpaRepository<MCQQuestion, Integer>{
	
}
