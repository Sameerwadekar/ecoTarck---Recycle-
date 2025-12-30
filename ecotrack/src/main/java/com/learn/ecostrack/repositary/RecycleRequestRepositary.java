package com.learn.ecostrack.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecostrack.entities.RecycleRequest;

public interface RecycleRequestRepositary extends JpaRepository<RecycleRequest, Integer> {
 	List<RecycleRequest> findByUserId(String userId);
}
