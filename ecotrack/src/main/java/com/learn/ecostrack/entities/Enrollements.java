package com.learn.ecostrack.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learn.ecostrack.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enrollements {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JsonManagedReference
	private User user;
	@ManyToOne
	@JsonManagedReference
	private WorkShop workShop;
	@CreationTimestamp
	private LocalDateTime enrollAt;
	private Double amount;
	private String razorpayOrderId;
	private String razorpayPaymentId;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;	
}
