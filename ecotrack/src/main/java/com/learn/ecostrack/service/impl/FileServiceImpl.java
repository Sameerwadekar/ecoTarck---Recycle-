package com.learn.ecostrack.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecostrack.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(MultipartFile multipartFile, String path) throws IOException {
		String originalFilename = multipartFile.getOriginalFilename();
		String fileName = UUID.randomUUID().toString();
		String extension = originalFilename.substring(originalFilename.indexOf("."));
		String fileNameAndExtension = fileName + extension;
		if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
			File folder = new File(path);
			 if (!folder.exists()) {
	               //create the folder
	               folder.mkdirs();
	           }
			 Files.copy(multipartFile.getInputStream(),Paths.get(path+fileNameAndExtension));
			 return fileNameAndExtension;
		} else {
			throw new RuntimeException("file with " + extension+ "is not allowed");
		}
	}

	@Override
	public InputStream getResource(String path, String name) throws FileNotFoundException {
		InputStream inputStream =  new FileInputStream(path+name);
		return inputStream;
	}

}
