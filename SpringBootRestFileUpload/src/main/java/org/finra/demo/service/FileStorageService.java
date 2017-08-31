package org.finra.demo.service;

import java.util.List;
import org.finra.demo.entity.FileStorageMetadata;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	public FileStorageMetadata save(MultipartFile file);
	public List<FileStorageMetadata> findAll();
	public FileStorageMetadata findByFileId(Long fileId);
}
