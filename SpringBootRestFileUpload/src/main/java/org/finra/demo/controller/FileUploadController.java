package org.finra.demo.controller;

import java.util.List;
import org.finra.demo.entity.FileStorageMetadata;
import org.finra.demo.exception.FileStorageException;
import org.finra.demo.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileStorageService fileStorageService;

	@PostMapping("/files")
	public FileStorageMetadata uploadFile(@RequestParam("file") MultipartFile file) {
		FileStorageMetadata res = fileStorageService.save(file);
		log.info("Successfully uploaded " + file.getOriginalFilename());
		return res;
	}
	
	@GetMapping("/files")
	public List<FileStorageMetadata> listAllUploadedFiles() throws FileStorageException{
		List<FileStorageMetadata> res = fileStorageService.findAll();
		if ( res.size() == 0) {
			throw new FileStorageException("No File found");
		} else {
			return fileStorageService.findAll();
		}
	}
	
	@GetMapping("/files/{fileId}")
	public FileStorageMetadata findUploadedFileMetadataByFileId(@PathVariable Long fileId) throws FileStorageException {
		FileStorageMetadata fsm = fileStorageService.findByFileId(fileId);
		if (fsm == null) {
			throw new FileStorageException("File with file Id: " + fileId + " not found");
		} else {
			return fileStorageService.findByFileId(fileId);
		}
	}
	
	@ExceptionHandler(FileStorageException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBadRequest(FileStorageException e) {
		return e.getMessage();
	}
	
}
