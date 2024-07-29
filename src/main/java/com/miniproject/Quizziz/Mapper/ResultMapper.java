package com.miniproject.Quizziz.Mapper;

import com.miniproject.Quizziz.dto.ResultDto;
import com.miniproject.Quizziz.model.Result;

public class ResultMapper {
	
	public Result dtoToEntity(ResultDto dto) {
		Result entity = new Result();
		entity.setName(dto.getName());
		entity.setQuiz_id(dto.getQuiz_id());	
		entity.setScore(dto.getScore());
		entity.setStudent_Fname(dto.getStudent_Fname());
		entity.setStudent_Lname(dto.getStudent_Lname());
		entity.setStudent_id(dto.getStudent_id());
		return entity;
	}
	
	public ResultDto entityToDto(Result entity) {
		ResultDto dto = new ResultDto();
		dto.setName(entity.getName());
		dto.setQuiz_id(entity.getQuiz_id());	
		dto.setScore(entity.getScore());
		dto.setStudent_Fname(entity.getStudent_Fname());
		dto.setStudent_Lname(entity.getStudent_Lname());
		dto.setStudent_id(entity.getStudent_id());
		dto.setResult_id(entity.getResult_id());
		return dto;
	}
}
