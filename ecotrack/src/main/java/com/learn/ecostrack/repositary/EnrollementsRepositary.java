package com.learn.ecostrack.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecostrack.entities.Enrollements;

public interface EnrollementsRepositary extends JpaRepository<Enrollements, Integer> {
	boolean existsByUserIdAndWorkShopId(String userId,int workShopId);
}
