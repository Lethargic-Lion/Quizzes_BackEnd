package com.miniproject.Quizziz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Optional<Student> findByUsernameAndPassword(String username, String password);
	
}
