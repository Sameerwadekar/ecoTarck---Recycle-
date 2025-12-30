package com.learn.ecostrack.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecostrack.dto.RecycleRequestDto;
import com.learn.ecostrack.service.FileService;
import com.learn.ecostrack.service.RecycleRequestService;

@RestController
@RequestMapping("/request")
public class RecycleRequestController {
	@Autowired private RecycleRequestService recycleRequestService;
	@Autowired private FileService fileService;
	
	@Value("${request.images}")
	private String image;
	
	@PostMapping("/{userId}")
	public ResponseEntity<RecycleRequestDto> newRequest(@RequestBody RecycleRequestDto recycleRequestDto,@PathVariable String userId){
		return new ResponseEntity<RecycleRequestDto>(recycleRequestService.addRequest(recycleRequestDto, userId),HttpStatus.CREATED);
	}
	
	@PutMapping("/reject/{id}")
	public ResponseEntity<RecycleRequestDto> rejectRequest(@RequestParam String reason,@PathVariable int id){
		return new ResponseEntity<RecycleRequestDto>(recycleRequestService.rejectRequest(id, reason),HttpStatus.OK);
	}
	
	@PutMapping("/approve/{id}")
	public ResponseEntity<RecycleRequestDto> approveRequest(@PathVariable int id){
		return new ResponseEntity<RecycleRequestDto>(recycleRequestService.approveRequest(id),HttpStatus.OK);
	}
	
	@PostMapping("/upload/{id}")
	public ResponseEntity<Map<String, String>> uploadImage(@PathVariable int id,@RequestParam("requestImage") MultipartFile file) throws IOException{
		String imageName = fileService.uploadFile(file, image);
		String imageUploaded = recycleRequestService.setRequestImage(imageName, id);
		 HashMap<String, String> response = new HashMap<String, String>();
		 response.put("imageName", imageUploaded);
		 response.put("message", "Image uploaded succesfully");
		return  new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
	}
}
