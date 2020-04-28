package com.omnicuris.OmniCurisException;

public class OmniCurisException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public OmniCurisException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
}