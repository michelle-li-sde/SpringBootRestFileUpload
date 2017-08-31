package org.finra.demo.repository;

import org.finra.demo.entity.FileStorageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStorageMetadataRepository extends JpaRepository<FileStorageMetadata, Long>{
	// Spring Data will generate everything dynamically at run-time
}
