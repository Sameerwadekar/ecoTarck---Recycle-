package com.learn.ecostrack.service;

import com.learn.ecostrack.dto.RecycleRequestDto;

public interface RecycleRequestService {
	RecycleRequestDto addRequest(RecycleRequestDto recycleRequestDto,String userId);
	RecycleRequestDto rejectRequest(int requestId,String reason);
	RecycleRequestDto approveRequest(int requestId);
	String setRequestImage(String image,int id);
}
