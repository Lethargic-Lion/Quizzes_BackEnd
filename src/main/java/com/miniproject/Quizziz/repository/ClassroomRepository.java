package com.miniproject.Quizziz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

}
