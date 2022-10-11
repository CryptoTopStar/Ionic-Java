package com.smart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="file_upload")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {

	public FileUpload(String fileName2, String fileDownloadUri, String contentType, long size2) {
		this.fileName = fileName2;
		this.path = fileDownloadUri;
		this.fileType = contentType;
		this.size = size2;
	}

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="path")
    private String path;
	
	@Column(name="file_type")
    private String fileType;
	
	@Column(name="size")
    private long size;

	
 }
