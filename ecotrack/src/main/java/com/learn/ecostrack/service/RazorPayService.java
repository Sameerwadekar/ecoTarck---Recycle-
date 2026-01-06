package com.learn.ecostrack.service;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

public interface RazorPayService {
	Order createOrder(Double amount) throws RazorpayException;
	boolean verifyPayment(String orderId,String paymentId,String signature) throws RazorpayException;
}
