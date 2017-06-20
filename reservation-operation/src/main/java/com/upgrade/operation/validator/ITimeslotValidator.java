package com.upgrade.operation.validator;

import com.upgrade.bean.Timeslot;
import com.upgrade.exception.InvalidTimeslotException;

public interface ITimeslotValidator {
	
	public boolean validate(Timeslot timeslot) throws InvalidTimeslotException; 
}
