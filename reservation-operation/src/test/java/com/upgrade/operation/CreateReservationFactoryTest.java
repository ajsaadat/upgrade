package com.upgrade.operation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import com.upgrade.bean.Reservation;
import com.upgrade.bean.Timeslot;
import com.upgrade.bean.User;
import com.upgrade.exception.ValidationException;
import com.upgrade.operation.validator.IReservationValidator;
import com.upgrade.operation.validator.impl.ReservationValidator;


@ContextConfiguration("classpath:config/BeanLocations.xml")
public class CreateReservationFactoryTest {
	
	@Test
	public void createReservationTest() throws ValidationException{
		IReservationValidator rValidator = new ReservationValidator() ; 
		CreateReservationFactory crFactory = new CreateReservationFactory(rValidator) ;
		User user = new User("A", "B", "C") ;
		Date startDate = new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS)) ; 
		
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		crFactory.createReservation(reservation) ; 
	}
	
	
	@Test
	/**
	 * This is invalid case, because reservation is being made for the same day.
	 */
	public void createInvalidReservationTest1() throws ValidationException{
		IReservationValidator rValidator = new ReservationValidator() ; 
		CreateReservationFactory crFactory = new CreateReservationFactory(rValidator) ;
		User user = new User("A", "B", "C") ;
		
		Date startDate = new Date(System.currentTimeMillis()) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS));
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		crFactory.createReservation(reservation) ; 
	}
	
	
	@Test
	/**
	 * This is invalid case, because end date is in the past.
	 */
	public void createInvalidReservationTest2() throws ValidationException{
		IReservationValidator rValidator = new ReservationValidator() ; 
		CreateReservationFactory crFactory = new CreateReservationFactory(rValidator) ;
		User user = new User("A", "B", "C") ;
		
		Date startDate = new Date(System.currentTimeMillis()) ;
		Date endDate = new Date(System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS));
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		crFactory.createReservation(reservation) ; 
	}
	
	@Test
	/**
	 * This is invalid case, because reservation duration is more than three days.
	 */
	public void createInvalidReservationTest3() throws ValidationException{
		IReservationValidator rValidator = new ReservationValidator() ; 
		CreateReservationFactory crFactory = new CreateReservationFactory(rValidator) ;
		User user = new User("A", "B", "C") ;
		
		Date startDate = new Date(System.currentTimeMillis()) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(4, TimeUnit.DAYS));
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		crFactory.createReservation(reservation) ; 
	}
	
	@Test
	/**
	 * This is invalid case, because start date is in the past.
	 */
	public void createInvalidReservationTest4() throws ValidationException{
		IReservationValidator rValidator = new ReservationValidator() ; 
		CreateReservationFactory crFactory = new CreateReservationFactory(rValidator) ;
		User user = new User("A", "B", "C") ;
		
		Date startDate = new Date(System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS)) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS));
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		crFactory.createReservation(reservation) ; 
	}
	
	@Test(threadPoolSize=100, invocationCount=100)
	public void createConcurrentReservationTest() throws ValidationException{
		IReservationValidator rValidator = new ReservationValidator() ; 
		CreateReservationFactory crFactory = new CreateReservationFactory(rValidator) ;
		List<Reservation> reservations = new ArrayList<Reservation>() ; 
		
		User user = new User(System.currentTimeMillis() + "", "B", "C") ;
		Date startDate = new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS)) ; 
		
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		reservations.add(reservation) ;
		
		/*user = new User("d", "e", "f") ;
		startDate = new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.convert(8, TimeUnit.DAYS)) ;
		endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(9, TimeUnit.DAYS)) ;
		timeslot = new Timeslot(startDate, endDate) ;
		Reservation secondReservation = new Reservation(user, timeslot) ; 
		reservations.add(secondReservation) ;*/
		for(Reservation res : reservations){
			crFactory.createReservation(res) ;
		}
 
		
	}
}
