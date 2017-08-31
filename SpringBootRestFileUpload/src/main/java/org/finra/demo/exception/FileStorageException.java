package org.finra.demo.exception;

public class FileStorageException extends RuntimeException{
	private static final long serialVersionUID = 8253704558445060350L;

	public FileStorageException(String message) {
		super(message);
	}
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
