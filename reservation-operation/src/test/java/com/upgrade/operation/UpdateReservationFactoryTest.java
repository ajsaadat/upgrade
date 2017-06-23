package com.upgrade.operation;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.upgrade.bean.Reservation;
import com.upgrade.bean.Timeslot;
import com.upgrade.bean.User;
import com.upgrade.exception.ReservationNotFoundException;
import com.upgrade.operation.bo.ReservationBO;

public class UpdateReservationFactoryTest {

	private UpdateReservationFactory urFactory ; 
	
	@BeforeClass
	public void init(){
		urFactory = new UpdateReservationFactory() ;
		
	}
	
	@Test
	public void updateReservation() throws ReservationNotFoundException{
		User user = new User("A", "B", "C") ;
		Date startDate = new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS)) ; 
		
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation resevation = new Reservation(user, timeslot) ; 
		
		ReservationBO reserBO = Mockito.mock(ReservationBO.class) ;
		Mockito.when(reserBO.findByID(Mockito.anyLong())).thenReturn(resevation) ; 
		
		user = new User("A", "B", "C") ;
		startDate = new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)) ;
		endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS)) ; 
		
		timeslot = new Timeslot(startDate, endDate) ;
		Reservation replacementResevation = new Reservation(user, timeslot) ; 
		
		urFactory.updateReservation(1234l, replacementResevation) ; 
	}
}
