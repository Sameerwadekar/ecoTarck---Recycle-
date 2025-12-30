package com.learn.ecostrack.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String uploadFile(MultipartFile multipartFile,String path) throws IOException;
	InputStream getResource(String path,String name) throws FileNotFoundException;
	
}
