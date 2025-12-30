package com.learn.ecostrack.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecostrack.dto.RecycleRequestDto;
import com.learn.ecostrack.entities.RecycleRequest;
import com.learn.ecostrack.entities.User;
import com.learn.ecostrack.enums.RequestStatus;
import com.learn.ecostrack.exception.NotFoundException;
import com.learn.ecostrack.repositary.RecycleRequestRepositary;
import com.learn.ecostrack.repositary.UserRepositary;
import com.learn.ecostrack.service.RecycleRequestService;

@Service
public class RecycleRequestServiceImpl implements RecycleRequestService {
	
	@Autowired RecycleRequestRepositary recycleRequestRepositary;
	@Autowired ModelMapper modelMapper;
	@Autowired UserRepositary userRepositary;
	
	@Override
	public RecycleRequestDto addRequest(RecycleRequestDto recycleRequestDto, String userId) {
		User user = userRepositary.findById(userId).orElseThrow(()-> new NotFoundException("User Not Found"));
		RecycleRequest request = modelMapper.map(recycleRequestDto, RecycleRequest.class);
		request.setUser(user);
		request.setRequestStatus(RequestStatus.PENDING);
		RecycleRequest savedRequest = recycleRequestRepositary.save(request);
		return modelMapper.map(savedRequest, RecycleRequestDto.class);
	}

	@Override
	public RecycleRequestDto rejectRequest(int requestId, String reason) {
		RecycleRequest request = recycleRequestRepositary.findById(requestId).orElseThrow(()-> new NotFoundException("Request Not Found"));
		request.setRequestStatus(RequestStatus.REJECTED);
		request.setReason(reason);
		RecycleRequest savedRequest = recycleRequestRepositary.save(request);
		return modelMapper.map(savedRequest, RecycleRequestDto.class);
	}

	@Override
	public RecycleRequestDto approveRequest(int requestId) {
		RecycleRequest request = recycleRequestRepositary.findById(requestId).orElseThrow(()-> new NotFoundException("Request Not Found"));
		request.setRequestStatus(RequestStatus.APPROVED);
		request.setReason(null);
		RecycleRequest savedRequest = recycleRequestRepositary.save(request);
		return modelMapper.map(savedRequest, RecycleRequestDto.class);
	}

	@Override
	public String setRequestImage(String image, int id) {
		RecycleRequest request = recycleRequestRepositary.findById(id).orElseThrow(()-> new NotFoundException("Request Not Found"));
		request.setItemImage(image);
		RecycleRequest savedRequest = recycleRequestRepositary.save(request);
		return savedRequest.getItemImage();
	}
}
