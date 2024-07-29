package com.miniproject.Quizziz.Mapper;

import com.miniproject.Quizziz.dto.MCQ_OptionDto;
import com.miniproject.Quizziz.model.MCQ_Option;

public class MCQ_OptionMapper {
	
	public MCQ_Option dtoToEntity(MCQ_OptionDto dto) {
		MCQ_Option entity = new MCQ_Option();
		entity.setOptionDescription(dto.getOptionDescription());
		entity.setCorrect(dto.isCorrect());
		return entity;
	}
	
	public MCQ_OptionDto entityToDto(MCQ_Option entity) {
		MCQ_OptionDto dto = new MCQ_OptionDto();
		dto.setOptionDescription(entity.getOptionDescription());
		dto.setCorrect(entity.isCorrect());
		return dto;
	}
}
