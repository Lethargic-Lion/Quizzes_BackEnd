package com.miniproject.Quizziz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	private String username;
	private String password;
	private String firstName;
    private String lastName;
    private String email;
	

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL , targetEntity = Quiz_Response.class)
	@JsonManagedReference
	@JsonIgnore
//	@JoinColumn(name = "student_id" , referencedColumnName = "student_id")
    private List<Quiz_Response> responses;
    
//    @OneToMany(cascade = CascadeType.ALL , targetEntity = Result.class)
//	@JoinColumn(name = "student_id" , referencedColumnName = "student_id")
//    private List<Result> results;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "student_classroom",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    @JsonIgnore
    private List<Classroom> classrooms;
}

