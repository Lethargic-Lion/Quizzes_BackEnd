package com.miniproject.Quizziz.dto;

import java.time.LocalDate;
import java.util.List;

import com.miniproject.Quizziz.model.Address;

import lombok.Data;

@Data
public class UserDto {
	private Long policyNumber;	
	private String customerId;
	private String employeeId;
	private String organizationName;
    private String insuranceCompany;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobile;
    private String ssn;
    private String occupation;
    private LocalDate dob;
    private List<Address> addresses;
}
