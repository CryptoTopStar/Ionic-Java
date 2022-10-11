package com.smart.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
	
	public String storeFile(MultipartFile file, String path, String fileName);
	public Resource loadFileAsResource(String fileName);
	void ensureDir(String path);

}
