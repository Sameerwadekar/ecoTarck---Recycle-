package com.learn.ecostrack.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecostrack.dto.EnrollementsDto;
import com.learn.ecostrack.service.EnrollementsService;
import com.learn.ecostrack.service.RazorPayService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/enroll")
public class EnrollementController {
	@Autowired private EnrollementsService enrollementsService;
	@Autowired private RazorPayService razorPayService;
	
	@PostMapping("/{userId}/workshop/{workShopId}")
	public ResponseEntity<EnrollementsDto> enroll(@PathVariable String userId,@PathVariable int workShopId){
		EnrollementsDto enrollement = enrollementsService.enroll(userId, workShopId);
		return ResponseEntity.ok(enrollement);
	}
	@PostMapping("/confirm")
	public ResponseEntity<String> confirmEnrollement(@RequestBody Map<String, String> payload) throws RazorpayException {
		
		boolean verified = razorPayService.verifyPayment(
				payload.get("razorpayOrderId"), 
				payload.get("razorpayPaymentId"), 
				payload.get("razorpaySignature"));
		
		if(!verified) {
			return ResponseEntity.badRequest().body("Payment verification failed");
		}
		enrollementsService.confirmPayment(
				payload.get("razorpayOrderId"),
				payload.get("razorpayPaymentId"));
		return ResponseEntity.ok("Enrollement Succesfull");
	}
}
