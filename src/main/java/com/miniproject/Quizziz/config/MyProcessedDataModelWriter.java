package com.miniproject.Quizziz.config;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.repository.BatchRegistrationRepo;

public class MyProcessedDataModelWriter implements ItemWriter<User> {
	
	@Autowired
	BatchRegistrationRepo batchRepo;

	
	@Override
	public void write(Chunk<? extends User> chunk) throws Exception {
		System.out.println("Chunk: "+chunk);
		batchRepo.saveAll(chunk);
	}


}
