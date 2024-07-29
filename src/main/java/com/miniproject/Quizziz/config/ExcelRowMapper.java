package com.miniproject.Quizziz.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

import com.miniproject.Quizziz.model.Address;
import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.model.UserKey;

public class ExcelRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(RowSet rs) throws Exception {
		return createObjectFromRow(rs.getCurrentRow());
	}
	
	public User createObjectFromRow(String[] row) {
		UserKey userKey = UserKey.builder().customerId(row[0])
				.employeeId(row[1]).organizationName(row[3])
				.build();
		
		User user = User.builder().userKey(userKey)
				.policyNumber(Long.valueOf(row[2]))
				.insuranceCompany(row[4])
				.firstName(row[5])
				.middleName(row[6])
				.lastName(row[7])
				.ssn(row[8])
				.email(row[9])
				.mobile(row[10])
				.dob(LocalDate.parse(row[11]))
				.occupation(row[12]).build();
		
		Address homeAddress = Address.builder()
				.type("Residence")
				.street(row[13])
				.state(row[14])
				.city(row[15])
				.telephone(row[16])
				.zipcode(row[17])
				.user(user).build();

		Address officeAddress = Address.builder()
				.type("Office")
				.street(row[18])
				.state(row[19])
				.city(row[20])
				.telephone(row[21])
				.zipcode(row[22])
				.user(user).build();
		
		List<Address> addresses = new ArrayList<>();
		addresses.add(homeAddress);
		addresses.add(officeAddress);
		user.setAddresses(addresses);
		return user;
	}

}
