package com.upgrade.operation;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.bean.Reservation;
import com.upgrade.bean.Timeslot;
import com.upgrade.exception.TimeslotAlreadyTakenException;
import com.upgrade.exception.ValidationException;
import com.upgrade.operation.validator.IReservationValidator;
/**
 * Responsible for creating a reservation. A reservation is created if
 * its timeslot does not violate previously made reservations. 
 * @author ajsaadat
 *
 */
public class CreateReservationFactory extends ReservationFactory{

	private IReservationValidator validator ; 

	public CreateReservationFactory(IReservationValidator validator){
		if(validator == null){
			throw new IllegalArgumentException("Validator can not be null.") ; 
		}
		this.validator = validator ; 
	}

	@Transactional(isolation=Isolation.DEFAULT, propagation=Propagation.REQUIRES_NEW)
	public Reservation createReservation(Reservation reservation) throws ValidationException{
		if(reservation == null){
			throw new IllegalArgumentException("Can not make a reservation without a reservation.") ;
		}
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
			throw new RuntimeException() ; 
		}
	}
}
