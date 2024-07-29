package com.miniproject.Quizziz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{
		
	@Query(value = "SELECT * FROM quiz WHERE classroom_id = ?1", nativeQuery = true)
	List<Quiz> getQuizByClassroomID(Long classroomID);
	
	@Query(value = "SELECT * FROM quiz WHERE classroom_id IS NULL", nativeQuery = true)
	List<Quiz> getAllQuizzes();
	
}
