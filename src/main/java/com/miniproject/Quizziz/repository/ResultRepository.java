package com.miniproject.Quizziz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.Result;

import jakarta.transaction.Transactional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

	@Query(value = "SELECT * FROM result WHERE quiz_id = ?1", nativeQuery = true)
	List<Result> getResultsByQuizID(Integer quiz_id);
	
	@Query(value = "SELECT * FROM result WHERE student_id=?1 AND quiz_id=?2" , nativeQuery = true)
	Result existsByStudentIdAndQuizId(Integer student_id, Integer quiz_id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM result WHERE student_id=?1 AND quiz_id=?2" , nativeQuery = true)
	void resetAttemptOfStudent(Integer student_id, Integer quiz_id);


}
