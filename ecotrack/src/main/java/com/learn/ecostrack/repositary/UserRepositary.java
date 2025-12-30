package com.learn.ecostrack.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecostrack.entities.User;
import java.util.Optional;


public interface UserRepositary extends JpaRepository<User, String>{
	Optional<User> findByEmail(String email);
}
