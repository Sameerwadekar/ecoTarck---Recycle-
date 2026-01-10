package com.learn.ecostrack.service.impl;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.learn.ecostrack.dto.EnrollementsDto;
import com.learn.ecostrack.entities.Enrollements;
import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.entities.WorkShop;
import com.learn.ecostrack.enums.PaymentStatus;
import com.learn.ecostrack.exception.NotFoundException;
import com.learn.ecostrack.repositary.EnrollementsRepositary;
import com.learn.ecostrack.repositary.UserRepositary;
import com.learn.ecostrack.repositary.WorkShopRepositary;
import com.learn.ecostrack.service.EmailService;
import com.learn.ecostrack.service.EnrollementsService;
import com.learn.ecostrack.service.RazorPayService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

@Service
public class EnrollementServiceImpl implements EnrollementsService {
	@Autowired private ModelMapper modelMapper;
	@Autowired private EnrollementsRepositary enrollementsRepositary;
	@Autowired private WorkShopRepositary workShopRepositary;
	@Autowired private UserRepositary userRepositary;
	@Autowired(required = false)
	private EmailService emailService;
	@Autowired private RazorPayService razorPayService;
	
	@Override
	public EnrollementsDto enroll(String userId, int workShopId) {
		if(enrollementsRepositary.existsByUserIdAndWorkShopId(userId, workShopId)) {
			throw new RuntimeException("user already enroll");
		}
		User user = userRepositary.findById(userId).orElseThrow(()->new NotFoundException("user not found"));
		WorkShop workShop = workShopRepositary.findById(workShopId).orElseThrow(()->new NotFoundException("workShop not found"));
		Order order;
		try {
			order = razorPayService.createOrder(workShop.getPrice());
		} catch (RazorpayException e) {
			throw new RuntimeException("unable to create order");
		}
		Enrollements enrollements = new Enrollements();
		enrollements.setUser(user);
		enrollements.setWorkShop(workShop);
		enrollements.setAmount(workShop.getPrice());
		enrollements.setPaymentStatus(PaymentStatus.CREATED);
		enrollements.setRazorpayOrderId(order.get("id"));
		Enrollements savedEnrollment = enrollementsRepositary.save(enrollements);
//		emailService.sendMail("snehalsameer2005@gmail.com", "enrollement completed" ,"for workshop thank you"+workShop.getName());
		if (emailService != null) {
		    emailService.sendMail(
		        "snehalsameer2005@gmail.com",
		        "enrollement completed",
		        "for workshop thank you " + workShop.getName()
		    );
		}

		return modelMapper.map(savedEnrollment, EnrollementsDto.class);
	}

	@Override
	public void confirmPayment(String orderId, String paymentId) {
		Enrollements enrollements = enrollementsRepositary.findByRazorpayOrderId(orderId).orElseThrow(()->new RuntimeException("enrollement not found"));
		enrollements.setRazorpayPaymentId(paymentId);
		enrollements.setPaymentStatus(PaymentStatus.SUCCESS);
		enrollementsRepositary.save(enrollements);
	}
}
