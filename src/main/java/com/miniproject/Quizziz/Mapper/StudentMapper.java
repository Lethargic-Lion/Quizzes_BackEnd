package com.miniproject.Quizziz.Mapper;

import com.miniproject.Quizziz.dto.StudentDto;
import com.miniproject.Quizziz.model.Student;

public class StudentMapper {
	
	public Student dtoToEntity(StudentDto dto) {
		Student entity = new Student();
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setClassrooms(dto.getClassrooms());
		entity.setResponses(dto.getResponses());
		
		return entity;
	}
	
	public StudentDto entityToDto(Student entity) {
		StudentDto dto = new StudentDto();
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setClassrooms(entity.getClassrooms());
		dto.setResponses(entity.getResponses());
		dto.setStudent_id(entity.getStudent_id());
		
		return dto;
	}

}
