package com.learn.ecostrack.service;

import com.learn.ecostrack.dto.EnrollementsDto;

public interface EnrollementsService {
	EnrollementsDto	enroll(String userId,int workShopId);
	void confirmPayment(String orderId,String paymentId);
}