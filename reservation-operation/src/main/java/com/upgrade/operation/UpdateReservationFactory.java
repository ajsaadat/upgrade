package com.upgrade.operation;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.bean.Reservation;
import com.upgrade.exception.ReservationNotFoundException;

public class UpdateReservationFactory extends ReservationFactory {

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
	public Reservation updateReservation(String id, Reservation newReservation) throws ReservationNotFoundException  {
		if(id == null || id.isEmpty()){
			throw new IllegalArgumentException("Reservation id can not be null or empty.") ;

		}else{
			Reservation reservation = reservationBO.findByID(id) ;
			if(reservation == null){
				throw new ReservationNotFoundException("Reservation [" + id + "] does not exist") ;
			}else{
				reservationBO.update(reservation);
				return reservation ; 

			}
		}
	}
}
