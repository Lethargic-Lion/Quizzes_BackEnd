package com.miniproject.Quizziz.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@ToString(exclude = "addresses")
public class User {
    
    @EmbeddedId
    private UserKey userKey;
    
    @Column(name = "policy_number", nullable = false)
    private Long policyNumber;	
    
    
    @Column(name = "insurance_company", nullable = false)
    private String insuranceCompany;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 50, nullable = false)
    private String middleName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false)
    @Pattern(regexp = "^(?=.{1,64})[A-Za-z0-9]+(.[A-Za-z0-9]+)@+[A-Za-z]+(.com)$")
    private String email;

    @Column(name = "mobile", length = 10, nullable = false)
    private String mobile;

    @Column(name = "SSN", length = 9, nullable = false)
    private String ssn;
    
    @Column(name = "occupation")
    private String occupation;

    @Column(name = "DOB", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference
    private List<Address> addresses;
}
