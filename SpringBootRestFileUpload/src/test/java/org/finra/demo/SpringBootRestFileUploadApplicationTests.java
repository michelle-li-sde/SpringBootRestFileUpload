package org.finra.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.finra.demo.controller.FileUploadController;
import org.finra.demo.entity.FileStorageMetadata;
import org.finra.demo.exception.FileStorageException;
import org.finra.demo.repository.FileStorageMetadataRepository;
import org.finra.demo.service.FileStorageService;
import org.finra.demo.service.FileStorageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestFileUploadApplicationTests {
	@Mock
	private FileStorageMetadataRepository fileStorageMetadataRepository;
	
	@InjectMocks
	private FileStorageServiceImpl fileStorageServiceImpl;
	
	private FileStorageMetadata fsmMock = new FileStorageMetadata();
	private List<FileStorageMetadata> listMock = new ArrayList<>();
	
	@Before
	public void init() throws IOException {
		MockitoAnnotations.initMocks(this);
		fsmMock.setFileId(100L);
		fsmMock.setFileName("test.txt");
		fsmMock.setFileSize(100L);
		fsmMock.setLocation("/temp");
		fsmMock.setUploadDate(100L);
		listMock.add(fsmMock);
		String TEST_LOCATION = System.getProperty("user.dir") + "/" + "test-dir";
		ReflectionTestUtils.setField(fileStorageServiceImpl, "ROOT_LOCATION", TEST_LOCATION);
		if (!new File(TEST_LOCATION).exists()) {
			Files.createDirectories(Paths.get(TEST_LOCATION));
		}
	}
	
	// Test Service save()
	@Test
	public void testSave() {
		MultipartFile file = new MockMultipartFile("test", "test.txt", MediaType.TEXT_PLAIN_VALUE,
				"Test File Content".getBytes());
		FileStorageMetadata testFile = fileStorageServiceImpl.save(file);
		assertTrue(new File(testFile.getLocation()).isFile() && testFile != null); 
	}
	// Test Service findAll()
	@Test
	public void testFindAll() {
		when(fileStorageMetadataRepository.findAll()).thenReturn(listMock);
		assertEquals(listMock, fileStorageServiceImpl.findAll());
	}
	
	// Test Service findByFileId()
	@Test
	public void testFindByFileId() {
		when(fileStorageMetadataRepository.findOne(100L)).thenReturn(fsmMock);
		assertEquals(fsmMock, fileStorageServiceImpl.findByFileId(100L));
	}
	
	@Mock
	FileStorageService fileStorageService;
	
	@InjectMocks
	FileUploadController fileUploadController;
	
	// Test Controller listAllUploadedFiles()
	@Test
	public void testListAllUploadedFiles() {
		when(fileStorageService.findAll()).thenReturn(listMock);
		assertEquals(listMock, fileUploadController.listAllUploadedFiles());
	}
	
	// Test Controller findUploadedFileMetadataByFileId()
	@Test(expected = FileStorageException.class)
	public void testFindUploadedFileMetadataByFileId() {
		when(fileStorageService.findByFileId(100L)).thenReturn(null);
		fileUploadController.findUploadedFileMetadataByFileId(100L);
	}
}
