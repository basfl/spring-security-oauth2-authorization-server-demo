package com.controller.errorHandler;

public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7587439821026797370L;

	public ResourceNotFoundException(String message) {
        super(message);
    }

}
