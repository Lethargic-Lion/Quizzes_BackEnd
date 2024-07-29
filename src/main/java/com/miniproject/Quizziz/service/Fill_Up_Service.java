package com.miniproject.Quizziz.service;

import java.util.List;

import com.miniproject.Quizziz.model.Fill_Ups;

public interface Fill_Up_Service {

	List<Fill_Ups> getAllFillUps();

	Fill_Ups createFillUp(Fill_Ups fill_up);

	Fill_Ups editFillUp(Long id, Fill_Ups fill_up);

	String deleteFillUp(Long id);

}
