package com.upgrade.operation.validator;

import com.upgrade.bean.Reservation;
import com.upgrade.exception.ValidationException;

public interface IReservationValidator {
	
	public boolean validate(Reservation reservation) throws ValidationException;
}
