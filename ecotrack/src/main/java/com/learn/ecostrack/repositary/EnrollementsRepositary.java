package com.learn.ecostrack.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecostrack.entities.Enrollements;
import java.util.List;
import java.util.Optional;


public interface EnrollementsRepositary extends JpaRepository<Enrollements, Integer> {
	boolean existsByUserIdAndWorkShopId(String userId,int workShopId);
	 Optional<Enrollements> findByRazorpayOrderId(String razorpayOrderId);
}
