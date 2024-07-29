package com.miniproject.Quizziz.Mapper;

import com.miniproject.Quizziz.dto.Fill_upsDto;
import com.miniproject.Quizziz.model.Fill_Ups;

public class Fill_upsMapper {
	
	public Fill_Ups dtoToEntity(Fill_upsDto dto) {
		Fill_Ups entity = new Fill_Ups();
		entity.setFillText(dto.getFillText());
		return entity;
	}
	
	public Fill_upsDto entityToDto(Fill_Ups entity) {
		Fill_upsDto dto = new Fill_upsDto();
		dto.setFillText(entity.getFillText());
		dto.setFill_up_Id(entity.getFill_up_Id());
		return dto;
	}
}
