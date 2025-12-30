package com.learn.ecostrack.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecostrack.dto.WorkShopDto;
import com.learn.ecostrack.entities.WorkShop;
import com.learn.ecostrack.exception.NotFoundException;
import com.learn.ecostrack.repositary.WorkShopRepositary;
import com.learn.ecostrack.service.WorkShopService;

@Service
public class WorkShopServiceimpl implements WorkShopService {
	@Autowired private ModelMapper modelMapper;
	@Autowired private WorkShopRepositary workShopRepositary;
	@Override
	public WorkShopDto addWorkShop(WorkShopDto workShopDto) {
		WorkShop savedWorkShop = workShopRepositary.save(modelMapper.map(workShopDto, WorkShop.class));
		return modelMapper.map(savedWorkShop, WorkShopDto.class);
	}

	@Override
	public WorkShopDto updateWorkShop(int id, WorkShopDto workShopDto) {
		workShopRepositary.findById(id).orElseThrow(()->new NotFoundException("WorkShop Not Found"));
		WorkShop workShop = modelMapper.map(workShopDto, WorkShop.class);
		workShop.setId(id);
		WorkShop savedWorkShop = workShopRepositary.save(workShop);
		return modelMapper.map(savedWorkShop, WorkShopDto.class);
	}

	@Override
	public WorkShopDto getWorkShopById(int id) {
		WorkShop workShop = workShopRepositary.findById(id).orElseThrow(()->new NotFoundException("WorkShop Not Found"));
		return modelMapper.map(workShop, WorkShopDto.class);
	}

	@Override
	public List<WorkShopDto> getAllWorkShopt() {
		return  workShopRepositary.findAll().stream().map(p->modelMapper.map(p, WorkShopDto.class)).toList();
	}

	@Override
	public void deleteWorkShop(int id) {
		WorkShop workShop = workShopRepositary.findById(id).orElseThrow(()->new NotFoundException("WorkShop Not Found"));
		workShopRepositary.delete(workShop);
	}

}