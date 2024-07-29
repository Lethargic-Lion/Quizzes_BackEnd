package com.miniproject.Quizziz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fill_Ups {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fill_up_Id;
	private String fillText;
	

}
