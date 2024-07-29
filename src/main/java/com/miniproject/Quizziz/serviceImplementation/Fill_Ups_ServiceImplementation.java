package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Fill_Ups;
import com.miniproject.Quizziz.repository.Fill_Up_Repository;
import com.miniproject.Quizziz.service.Fill_Up_Service;

@Service
public class Fill_Ups_ServiceImplementation implements Fill_Up_Service{
	
	@Autowired Fill_Up_Repository fillupRepo;
	
	@Override
	public List<Fill_Ups> getAllFillUps() {
		return fillupRepo.findAll();
	}

	@Override
	public Fill_Ups createFillUp(Fill_Ups fill_up) {
		return fillupRepo.save(fill_up);
	}

	@Override
	public Fill_Ups editFillUp(Long id, Fill_Ups fill_up) {
		Fill_Ups updatedFillUp = fillupRepo.findById(id).orElse(null);
		updatedFillUp.setFillText(fill_up.getFillText());
		fillupRepo.save(updatedFillUp);
		return updatedFillUp;
	}

	@Override
	public String deleteFillUp(Long id) {
		fillupRepo.deleteById(id);
		return "Fill Up details have been deleted";
	}
}
