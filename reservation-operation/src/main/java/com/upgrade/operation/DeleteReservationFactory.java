package com.upgrade.operation;

import com.upgrade.bean.Reservation;
import com.upgrade.exception.CancellationException;
import com.upgrade.exception.ReservationNotFoundException;

public class DeleteReservationFactory extends ReservationFactory {

	
	
	public boolean cancelReservation(String id) throws CancellationException{
		if(id == null || id.isEmpty()){
			throw new IllegalArgumentException("Reservation id can not be null or empty.") ;
			
		}else{
			Reservation reservation = reservationBO.findByID(id) ;
			if(reservation == null){
				throw new ReservationNotFoundException("Reservation [" + id + "] does not exist") ;
			}else{
				try{
					reservationBO.delete(reservation);
					return true ; 
				}catch(Throwable t){
					throw new CancellationException(t.getMessage()) ; 
				}
				
			}
		}
	}
}
