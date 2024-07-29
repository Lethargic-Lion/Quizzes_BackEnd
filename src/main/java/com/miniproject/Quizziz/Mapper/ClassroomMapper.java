package com.miniproject.Quizziz.Mapper;

import com.miniproject.Quizziz.dto.ClassroomDto;
import com.miniproject.Quizziz.model.Classroom;

public class ClassroomMapper {
	
	public Classroom dtoToEntity(ClassroomDto dto) {
		Classroom entity = new Classroom();
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setStudents(dto.getStudents());
		entity.setTeacher(dto.getTeacher());
		entity.setQuizzes(dto.getQuizzes());
		return entity;
	}
	
	public ClassroomDto entityToDto(Classroom entity) {
		ClassroomDto dto = new ClassroomDto();
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setStudents(entity.getStudents());
		dto.setTeacher(entity.getTeacher());
		dto.setQuizzes(entity.getQuizzes());
		dto.setClassroom_id(entity.getClassroom_id());
		return dto;
	}
}
