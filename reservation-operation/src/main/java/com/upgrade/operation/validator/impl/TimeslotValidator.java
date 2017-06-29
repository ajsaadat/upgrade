package com.upgrade.operation.validator.impl;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import com.upgrade.bean.Timeslot;
import com.upgrade.exception.InvalidTimeslotException;
import com.upgrade.operation.validator.ITimeslotValidator;

public class TimeslotValidator implements ITimeslotValidator {

	public boolean validate(Timeslot timeslot) throws InvalidTimeslotException {
		Date startDate = timeslot.getStartDate() ; 
		Date endDate = timeslot.getEndDate() ; 
		boolean validStartDate = isStartDateInFuture(startDate) ;
		if(!validStartDate){
			throw new InvalidTimeslotException("Start date is not valid.") ; 
		}
		boolean validEndDate = isEndDateInFuture(endDate) ; 
		if(!validEndDate){
			throw new InvalidTimeslotException("End date is not valid.") ; 
		}
		boolean validDuration = isDurationValid(startDate, endDate) ; 
		if(!validDuration){
			throw new InvalidTimeslotException("Duration of reservation is not valid.") ;
		}
		return true;
	}
	
	
	public boolean isStartDateInFuture(Date startDate){
		long now = System.currentTimeMillis()  ;
		long startDateLong = startDate.getTime() ;
		long dayLong = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		if(startDateLong - (now + dayLong) < 0){
			return false ; 
		}else {
			return true ; 
		}
	}
	
	public boolean isEndDateInFuture(Date endDate){
		long now = System.currentTimeMillis()  ;
		long endDateLong = endDate.getTime() ;
		long dayLong = TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS);
		if(endDateLong - (now + dayLong) < 0){
			return false ; 
		}else {
			return true ; 
		}
	}
	
	public boolean isDurationValid(Date startDate, Date endDate){
		long startDateLong = startDate.getTime() ;
		long endDateLong = endDate.getTime() ;
		
		long threeDaysLong = TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS);
		
		if(endDateLong - startDateLong > threeDaysLong){
			return false ; 
		}else{
			return true ; 
		}
		
	}

}
