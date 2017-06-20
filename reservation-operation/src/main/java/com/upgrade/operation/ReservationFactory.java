package com.upgrade.operation;

import java.sql.Date;

import com.upgrade.bean.Reservation;
import com.upgrade.bean.Timeslot;
import com.upgrade.bean.User;
import com.upgrade.operation.validator.IReservationValidator;

public class ReservationFactory {
	
	private IReservationValidator validator ; 
	
	public ReservationFactory(IReservationValidator validator){
		this.validator = validator ; 
	}
	
	public Reservation createReservation(Date startDate, Date endDate, String firstName, String lastName, String email){
		Timeslot timeslot = new Timeslot(startDate, endDate) ; 
		User user = new User(firstName, lastName, email) ;
		
		Reservation reservation = new Reservation(user, timeslot) ; 
		
		boolean valid = validator.validate(reservation) ;
		if(valid){
			return reservation ; 
		}else{
			//TODO
			throw new RuntimeException() ; 
		}
		
	}
}
