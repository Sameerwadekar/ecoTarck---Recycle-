package com.learn.ecostrack.service;

import java.util.List;

import com.learn.ecostrack.dto.WorkShopDto;

public interface WorkShopService {
	WorkShopDto addWorkShop(WorkShopDto workShopDto);
	WorkShopDto updateWorkShop(int id, WorkShopDto workShopDto);
	WorkShopDto getWorkShopById(int id);
	List<WorkShopDto> getAllWorkShopt();
	void deleteWorkShop(int id);
}
