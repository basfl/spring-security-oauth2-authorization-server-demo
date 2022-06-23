package com.controller.errorHandler;

public class ResourceAlreadyExists extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4889764053314122792L;

	/**
	 * 
	 */
	

	public ResourceAlreadyExists(String message) {
        super(message);
    }
}
