package com.smart.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.dao.IFileUploadDao;
import com.smart.dao.repo.IFileUploadRepository;
import com.smart.generic.impl.GenericDaoImpl;
import com.smart.model.FileUpload;

@Repository
public class FileUploadDaoImpl extends GenericDaoImpl<FileUpload> implements IFileUploadDao{

	@Autowired
	IFileUploadRepository repo;
	
	
	@Override
	public Long count() {
		return repo.count();
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public FileUpload findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public FileUpload save(FileUpload entity) {
		return repo.save(entity);
	}

	@Override
	public List<FileUpload> findAll() {
		return repo.findAll();
	}

}
