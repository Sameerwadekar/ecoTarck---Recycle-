package com.learn.ecostrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecostrack.dto.EnrollementsDto;
import com.learn.ecostrack.service.EnrollementsService;

@RestController
@RequestMapping("/enroll")
public class EnrollementController {
	@Autowired private EnrollementsService enrollementsService;
	
	@PostMapping("/{userId}/workshop/{workShopId}")
	public ResponseEntity<EnrollementsDto> enroll(@PathVariable String userId,@PathVariable int workShopId){
		EnrollementsDto enrollement = enrollementsService.enroll(userId, workShopId);
		return ResponseEntity.ok(enrollement);
	}
}
