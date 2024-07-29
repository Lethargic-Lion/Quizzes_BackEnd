package com.miniproject.Quizziz.config;

import java.util.Objects;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;

import com.miniproject.Quizziz.model.User;

public class CustomItemProcessor implements ItemProcessor<Row, Objects[]> {

	@Override
	public Objects[] process(Row item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
