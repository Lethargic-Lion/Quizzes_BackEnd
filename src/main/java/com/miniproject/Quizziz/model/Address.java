package com.miniproject.Quizziz.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Address")
@ToString(exclude = "user")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "type cannot be null")
    @Column(name = "type", nullable = false)
    private String type;

    @NotBlank(message = "street cannot be null")
    @Column(name = "street", nullable = false)
    private String street;

    @NotBlank(message = "state cannot be null")
    @Column(name = "state", nullable = false)
    private String state;

    @NotBlank(message = "city cannot be null")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "zipcode cannot be null")
    @Column(name = "zipcode", nullable = false)
    private String zipcode;


    @Column(name = "telephone")
    private String telephone;

    @ManyToOne
    @JoinColumns({
    	@JoinColumn(name = "customer_id" , referencedColumnName = "customerId"),
    	@JoinColumn(name = "employee_id" , referencedColumnName = "employeeId"),
    	@JoinColumn(name = "organization_name" , referencedColumnName = "organizationName")
    })
    @JsonBackReference
     private User user;
}