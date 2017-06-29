package com.upgrade.exception;

public class ReservationNotFoundException extends CancellationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3788251580735339706L;

	public ReservationNotFoundException(String message) {
		super(message);
	}

}
