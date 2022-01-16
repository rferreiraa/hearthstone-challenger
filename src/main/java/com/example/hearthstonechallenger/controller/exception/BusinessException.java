package com.example.hearthstonechallenger.controller.exception;

public class BusinessException extends Exception {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String errorMessage) {
	        super(errorMessage);
	    }
}
