package com.upgrade.operation.validator.impl;

import com.upgrade.bean.Reservation;
import com.upgrade.bean.Timeslot;
import com.upgrade.bean.User;
import com.upgrade.exception.InvalidTimeslotException;
import com.upgrade.exception.InvalidUserException;
import com.upgrade.operation.validator.IReservationValidator;
import com.upgrade.operation.validator.ITimeslotValidator;
import com.upgrade.operation.validator.IUserValidator;

public class ReservationValidator implements IReservationValidator {

	private ITimeslotValidator timeslotValidator = new TimeslotValidator() ;
	private IUserValidator userValidator = new UserValidator() ; 
	
	public boolean validate(Reservation reservation) throws InvalidTimeslotException, InvalidUserException {
		User user = reservation.getUser() ; 
		Timeslot timeslot = reservation.getTimeslot() ; 
		boolean validUser = userValidator.validate(user) ; 
		boolean validTimeslot = timeslotValidator.validate(timeslot) ; 
		if(validUser && validTimeslot){
			return true ; 
		}else {
			return false;
		}
		
	}

}
