package com.learn.ecostrack.dto;

import java.time.LocalDateTime;


import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.entities.WorkShop;
import com.learn.ecostrack.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollementsDto {
	private User user;
	private WorkShop workShop;
	private LocalDateTime enrollAt;
	private Double amount;
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private PaymentStatus paymentStatus;	
}
