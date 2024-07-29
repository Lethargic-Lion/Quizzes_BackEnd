package com.miniproject.Quizziz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.miniproject.Quizziz.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	
	@Query(value = "SELECT * FROM question WHERE quiz_id = ?1", nativeQuery = true)
    List<Question> findQuestionsByQuizId(Integer quizId);
}
