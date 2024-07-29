package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.MCQ_Option;
import com.miniproject.Quizziz.repository.MCQ_Option_Repository;
import com.miniproject.Quizziz.service.MCQ_Option_Service;

@Service
public class MCQ_Option_ServiceImplementation implements MCQ_Option_Service {

	@Autowired
	MCQ_Option_Repository optionRepo;

	@Override
	public List<MCQ_Option> getAllOptions() {
		return optionRepo.findAll();
	}

	@Override
	public MCQ_Option createOption(MCQ_Option option) {
		System.out.println(optionRepo.save(option));
		return optionRepo.save(option);
	}

	@Override
	public MCQ_Option editOption(Long id, MCQ_Option option) {
		MCQ_Option updatedOption = optionRepo.findById(id).orElse(null);
		updatedOption.setOptionDescription(option.getOptionDescription());
		
		if (option.isCorrect()) {
			updatedOption.setCorrect(true);
		} else {
			updatedOption.setCorrect(false);
		}
		optionRepo.save(updatedOption);
		return updatedOption;
	}

	@Override
	public String deleteOption(Long id) {
		optionRepo.deleteById(id);
		return "Option has been deleted";
	}

}
