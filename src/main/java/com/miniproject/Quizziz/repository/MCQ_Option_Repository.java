package com.miniproject.Quizziz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.MCQ_Option;

@Repository
public interface MCQ_Option_Repository extends JpaRepository<MCQ_Option, Long>{

}
