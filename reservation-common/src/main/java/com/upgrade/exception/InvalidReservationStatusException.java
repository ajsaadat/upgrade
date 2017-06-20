package com.upgrade.exception;

public class InvalidReservationStatusException extends CancellationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2366389862540771921L;

	public InvalidReservationStatusException(String message) {
		super(message);
	}

}
