package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.MCQ_Option;

public interface MCQ_Option_Service {

	List<MCQ_Option> getAllOptions();

	MCQ_Option createOption(MCQ_Option option);

	MCQ_Option editOption(Long id, MCQ_Option option);

	String deleteOption(Long id);

}
