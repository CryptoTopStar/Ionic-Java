package com.smart.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smart.model.FileUpload;
import com.smart.service.IFileStorageService;
import com.smart.service.IUserService;
import com.smart.util.Constants;

@RestController
@CrossOrigin("*")
public class FileUploadController {
	

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private IFileStorageService fileStorageService;
	
	@Autowired
	IUserService userService;
	  
		@GetMapping("/downloadFile/{fileName:.+}") 
		public ResponseEntity<Resource>
		downloadFile(@PathVariable String fileName, HttpServletRequest request) { //
			Resource resource = fileStorageService.loadFileAsResource(fileName);
			return getResourceResponse(resource,request);
			
		}
		
		private ResponseEntity<Resource> getResourceResponse(Resource resource,  HttpServletRequest request) {
			String contentType = null; 
			try {
					contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()); 
				} catch (IOException ex) { 
					logger.info("Could not determine file type."); 
				}
				if(contentType == null) { 
					contentType = "application/octet-stream"; 
				}

					return ResponseEntity.ok()
							.contentType(MediaType.parseMediaType(contentType))
							.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
									resource.getFilename() + "\"") .body(resource); 
		}
		
		
		@PostMapping("/uploadUserProfileImage/{userId}")
	    public FileUpload uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("userId") Long userId) {
			FileUpload uf = userService.uploadUserImage(file,userId);
			return uf;
	    }
		
		
		
	/*
	 * @PostMapping("/uploadMultipleFiles") public List<FileUpload>
	 * uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) { return
	 * Arrays.asList(files) .stream() .map(file -> uploadFile(file))
	 * .collect(Collectors.toList()); }
	 */
		
}
