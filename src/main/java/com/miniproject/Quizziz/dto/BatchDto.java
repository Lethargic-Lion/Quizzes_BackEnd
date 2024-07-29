package com.miniproject.Quizziz.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchDto {
	private String customerId;
    private String employeeId;
    private Long policyNumber;
    private String organization;
    private String insuranceCompany;
    private String firstName;
    private String middleName;
    private String lastName;
    private String ssn;
    private String email;
    private String mobile;
    private LocalDate dob;
    private String occupation;
    private String residenceStreet;
    private String residenceState;
    private String residenceCity;
    private String residenceZipcode;
    private String residenceTelephone;
    private String officeStreet;
    private String officeState;
    private String officeCity;
    private String officeZipcode;
    private String officeTelephone;

}
