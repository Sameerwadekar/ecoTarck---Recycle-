package com.learn.ecostrack.service.impl;

import java.sql.Date;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecostrack.dto.EnrollementsDto;
import com.learn.ecostrack.entities.Enrollements;
import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.entities.WorkShop;
import com.learn.ecostrack.exception.NotFoundException;
import com.learn.ecostrack.repositary.EnrollementsRepositary;
import com.learn.ecostrack.repositary.UserRepositary;
import com.learn.ecostrack.repositary.WorkShopRepositary;
import com.learn.ecostrack.service.EmailService;
import com.learn.ecostrack.service.EnrollementsService;

@Service
public class EnrollementServiceImpl implements EnrollementsService {
	@Autowired private ModelMapper modelMapper;
	@Autowired private EnrollementsRepositary enrollementsRepositary;
	@Autowired private WorkShopRepositary workShopRepositary;
	@Autowired private UserRepositary userRepositary;
	@Autowired private EmailService emailService;
	
	@Override
	public EnrollementsDto enroll(String userId, int workShopId) {
		if(enrollementsRepositary.existsByUserIdAndWorkShopId(userId, workShopId)) {
			throw new RuntimeException("user already enroll");
		}
		User user = userRepositary.findById(userId).orElseThrow(()->new NotFoundException("user not found"));
//		user.getEmail()
		WorkShop workShop = workShopRepositary.findById(workShopId).orElseThrow(()->new NotFoundException("workShop not found"));
		Enrollements enrollements = new Enrollements();
		enrollements.setUser(user);
		enrollements.setWorkShop(workShop);
		Enrollements savedEnrollment = enrollementsRepositary.save(enrollements);
		emailService.sendMail("snehalsameer2005@gmail.com", "enrollement completed" ,"for workshop thank you"+workShop.getName());
		return modelMapper.map(savedEnrollment, EnrollementsDto.class);
	}
}
