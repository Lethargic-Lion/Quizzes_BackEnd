package com.miniproject.Quizziz.config;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.miniproject.Quizziz.model.User;

public class ExcelItemWriter implements ItemWriter<User> {

	@Override
	public void write(Chunk<? extends User> chunk) throws Exception {
		// TODO Auto-generated method stub

	}

}
