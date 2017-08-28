package org.finra.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileStorageMetadata implements Serializable{
	private static final long serialVersionUID = 8085563971445218359L;
	@Id
	@GeneratedValue
	private Long file_id;
	
	@Column(nullable = false)
	private String file_name;
	
	@Column(nullable = false)
	private Long file_size;
	
	@Column(nullable = false)
	private String upload_date;
	
	@Column
	private String location = "upload-dir";
	
	@Column
	private Long user_id;

	public Long getFile_id() {
		return file_id;
	}

	public void setFile_id(Long file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

	public String getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
