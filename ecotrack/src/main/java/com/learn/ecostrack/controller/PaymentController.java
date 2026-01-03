package com.learn.ecostrack.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
public class PaymentController {
	@Value("${razorpay.key}")
	private String razorpayKey;
	@Value("${razorpay.secret}")
	private String razorpaySecret;
	@PostMapping("/payment")
	public ResponseEntity<Map<String, String>> payment(){
		try {
			RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);
		} catch (RazorpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
