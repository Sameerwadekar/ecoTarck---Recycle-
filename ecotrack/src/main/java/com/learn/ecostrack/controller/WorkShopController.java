package com.learn.ecostrack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecostrack.dto.WorkShopDto;
import com.learn.ecostrack.service.WorkShopService;

@RestController
@RequestMapping("/workshops")
public class WorkShopController {

    private final UserController userController;
	@Autowired private WorkShopService workShopService;

    WorkShopController(UserController userController) {
        this.userController = userController;
    }
	
	@PostMapping
	public ResponseEntity<WorkShopDto> createWorkShop(@RequestBody WorkShopDto workShopDto){
		return new ResponseEntity<WorkShopDto>(workShopService.addWorkShop(workShopDto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<WorkShopDto>> getAllWorkShops(@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "2") int pageSize){
		return ResponseEntity.ok(workShopService.getAllWorkShopt(pageNumber,pageSize));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WorkShopDto> getWorkShopById(@PathVariable int id){
		return ResponseEntity.ok(workShopService.getWorkShopById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteWorkShopbyID(@PathVariable Integer id){
		workShopService.deleteWorkShop(id);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", id+ " - WorkShopDeleted");
		return ResponseEntity.ok(map);
	}
	@PutMapping("/{id}")
	public ResponseEntity<WorkShopDto> updateWorkShop(@RequestBody WorkShopDto workShopDto,@PathVariable int id){
		return ResponseEntity.ok(workShopService.updateWorkShop(id,workShopDto));
	}
	
}
