package com.miniproject.Quizziz.config;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;

import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.serviceImplementation.BatchRegistrationService;

public class ExcelItemProcessor implements ItemProcessor<User, User> {

    private BatchRegistrationService batchRegistrationService;

    public ExcelItemProcessor(BatchRegistrationService batchRegistrationService) {
        this.batchRegistrationService = batchRegistrationService;
    }

	@Override
	public User process(User item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

