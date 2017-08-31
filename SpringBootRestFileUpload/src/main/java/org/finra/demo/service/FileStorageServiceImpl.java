package org.finra.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.finra.demo.entity.FileStorageMetadata;
import org.finra.demo.exception.FileStorageException;
import org.finra.demo.repository.FileStorageMetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
public class FileStorageServiceImpl implements FileStorageService{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileStorageMetadataRepository fileStorageMetadataRepository;
	
	@Value(value = "${file.upload.location}")
	private String ROOT_LOCATION;
	
	@Override
	@Transactional
	public FileStorageMetadata save(MultipartFile file) {
		FileStorageMetadata metadata = convertMultipartFileToMetadata(file);
		try {
			if (!new File(ROOT_LOCATION).exists()) {
				Files.createDirectories(Paths.get(ROOT_LOCATION));
			}
			file.transferTo(new File(metadata.getLocation()));
			fileStorageMetadataRepository.save(metadata);
		} catch (IOException e) {
			log.error("Failed to store file " + file.getOriginalFilename());
			throw new FileStorageException("Failed to store file " + file.getOriginalFilename() +  "to location: " + metadata.getLocation(), e);		
		}
		return metadata;
	}
	
	@Override
	public List<FileStorageMetadata> findAll() {
		return fileStorageMetadataRepository.findAll();
	}

	@Override
	public FileStorageMetadata findByFileId(Long fileId) {
		return fileStorageMetadataRepository.findOne(fileId);
	}
	
	private FileStorageMetadata convertMultipartFileToMetadata(MultipartFile file) {
		FileStorageMetadata fsm = new FileStorageMetadata();
		Long currentTime = System.currentTimeMillis();
		String filename = currentTime + "_" + file.getOriginalFilename();
		fsm.setFileName(filename);
		fsm.setFileSize(file.getSize());
		fsm.setUploadDate(currentTime);
		fsm.setLocation(ROOT_LOCATION + "/" + filename);
		return fsm;
	}
}
