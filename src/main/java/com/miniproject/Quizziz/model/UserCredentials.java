//package com.miniproject.Quizziz.model;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//
//@Data
//@Entity
//@Builder
//@AllArgsConstructor
//@Table(name = "User_Credentials")
//public class UserCredentials implements Serializable{	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @Column(name = "email_id")
//    @NotBlank(message = "Email must not be null")
//    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$",
//            message = "Email must be valid")
//    private String email;
//    
//    @Column(name = "password")
//    @NotBlank(message = "Password must not be null")
//    private String password;
//    
//    @Column(name = "LAST_LOGGED_IN")
//    private LocalDateTime last_logged_in;
//    
//    @Column(name = "FLAG")
//    private boolean flag;
//    
//    @Column(name = "member_since")
//    private LocalDateTime memberSince;	
//}
