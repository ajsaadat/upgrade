package com.upgrade.operation;

import java.sql.Date;
import java.util.List;

import com.upgrade.bean.Reservation;
import com.upgrade.bean.Timeslot;
import com.upgrade.bean.User;
import com.upgrade.exception.TimeslotAlreadyTakenException;
import com.upgrade.exception.ValidationException;
import com.upgrade.operation.validator.IReservationValidator;

public class CreateReservationFactory extends ReservationFactory{

	private IReservationValidator validator ; 

	public CreateReservationFactory(IReservationValidator validator){
		if(validator == null){
			throw new IllegalArgumentException("Validator can not be null.") ; 
		}
		this.validator = validator ; 
	}

	public Reservation createReservation(Date startDate, Date endDate, 
			String firstName, String lastName, String email) throws ValidationException{
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

	public Reservation createReservation(Reservation reservation) throws ValidationException{
		boolean valid = validator.validate(reservation) ;
		if(valid){
			Timeslot timeslot = reservation.getTimeslot() ; 
			List<Timeslot> timeslots = timeslotBO.findByRange(timeslot.getStartDate(), timeslot.getEndDate()) ;
			if(timeslots.isEmpty()){
				reservationBO.save(reservation);
				return reservation ; 
			}else{
				throw new TimeslotAlreadyTakenException("Timeslot [" + timeslot + " has already been taken.") ; 
			}
		}else{
			//TODO
			throw new RuntimeException() ; 
		}
	}
}
