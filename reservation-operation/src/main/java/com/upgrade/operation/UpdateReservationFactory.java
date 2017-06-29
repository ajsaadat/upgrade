package com.upgrade.operation;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.bean.Reservation;
import com.upgrade.exception.ReservationNotFoundException;

public class UpdateReservationFactory extends ReservationFactory {

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
	public Reservation updateReservation(long id, Reservation newReservation) throws ReservationNotFoundException  {

		Reservation reservation = reservationBO.findByID(id) ;
		if(reservation == null){
			throw new ReservationNotFoundException("Reservation [" + id + "] does not exist") ;
		}else{
			reservationBO.update(reservation);
			return reservation ; 

		}
	}
}
