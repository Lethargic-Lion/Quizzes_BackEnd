package com.miniproject.Quizziz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
