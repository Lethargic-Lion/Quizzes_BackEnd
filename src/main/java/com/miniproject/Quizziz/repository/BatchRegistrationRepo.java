package com.miniproject.Quizziz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject.Quizziz.model.User;
import com.miniproject.Quizziz.model.UserKey;

@Repository
public interface BatchRegistrationRepo extends JpaRepository<User, UserKey>{
	
}
