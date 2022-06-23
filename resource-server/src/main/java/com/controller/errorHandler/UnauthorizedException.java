package com.controller.errorHandler;

public class UnauthorizedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 850313345164404687L;

	public UnauthorizedException(String message) {
        super(message);
    }

}
