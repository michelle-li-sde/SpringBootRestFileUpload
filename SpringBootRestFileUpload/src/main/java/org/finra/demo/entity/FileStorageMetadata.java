package org.finra.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileStorageMetadata implements Serializable{
	private static final long serialVersionUID = 8085563971445218359L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fileId;
	
	@Column(nullable = false)
	private String fileName;
	
	@Column(nullable = false)
	private Long fileSize;
	
	@Column(nullable = false)
	private Long uploadDate;
	
	@Column
	private String location;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Long uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
