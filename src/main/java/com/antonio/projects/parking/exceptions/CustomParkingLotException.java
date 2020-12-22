package com.antonio.projects.parking.exceptions;

public class CustomParkingLotException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private int status;
	private String info;
	private String exceptionName;

	public CustomParkingLotException(String message, int status, String info, String exceptionName) {
		this.message = message;
		this.status = status;
		this.info = info;
		this.exceptionName = exceptionName;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public String getInfo() {
		return info;
	}

	public String getExceptionName() {
		return exceptionName;
	}

}
