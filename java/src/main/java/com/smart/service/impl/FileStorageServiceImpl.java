package com.smart.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.smart.exception.FileStorageException;
import com.smart.exception.RecordNotFoundException;
import com.smart.property.FileStorageProperties;
import com.smart.service.IFileStorageService;


@Service
public class FileStorageServiceImpl implements IFileStorageService {

	private final Path fileStorageLocation;
	private final String uploadPath;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
    	this.uploadPath = fileStorageProperties.getUploadDir();
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    public String storeFile(MultipartFile file, String path,String name) {
        // Normalize file name
    	StringBuilder sb = new StringBuilder();
    	if(path != null && !path.isEmpty()) {
    		sb.append(path+"/");
    	}
    	String newFileName = name + "." + getFileExtension(file.getOriginalFilename());
    	String fileName = sb.append(StringUtils.cleanPath(newFileName)).toString();
    	try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return newFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new RecordNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RecordNotFoundException("File not found " + fileName, ex);
        }
    }
    
    @Override
    public void ensureDir(String path) {
    	try {
    		Path p =  Paths.get(uploadPath +"/"+ path);
    		if(!Files.exists(p)) {
    			Files.createDirectories(p);
    		}
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
}
