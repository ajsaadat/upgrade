package com.upgrade.restapi;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upgrade.bean.Reservation;
import com.upgrade.exception.ValidationException;
import com.upgrade.operation.CreateReservationFactory;
import com.upgrade.operation.validator.IReservationValidator;
import com.upgrade.operation.validator.impl.ReservationValidator;

@RestController
public class ReservationController {

	private CreateReservationFactory rFactory ; 
	
	@RequestMapping(path="/reservation", method= RequestMethod.POST)
	public String createReservation(@RequestParam Reservation reservation){
		IReservationValidator rValidator = new ReservationValidator() ;
		rFactory = new CreateReservationFactory(rValidator) ;
		
		try {
			Reservation confirmedReservation = rFactory.createReservation(reservation) ;
			return confirmedReservation.getId() ; 
			
		} catch (ValidationException e) {
			throw new RuntimeException(e) ;
		}
	}
	
	@RequestMapping(path="/reservation", method= RequestMethod.PUT)
	public long updateReservation(@RequestParam Reservation newReservation, @RequestParam long reservationID){
		return 0;
	}
	
	@RequestMapping(path="/reservation", method= RequestMethod.DELETE)
	public boolean cancelReservation(@RequestParam long reservationID){
		return false ;
	}
	
	@RequestMapping(path="/reservation", method= RequestMethod.GET)
	public List<Reservation> cancelReservation(@RequestParam Date lowerBound, @RequestParam Date higherBound){
		List<Reservation> reservations = new ArrayList<>() ; 
		return reservations ; 
	}
	
	
	
	
}
