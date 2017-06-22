package com.upgrade.operation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.upgrade.bean.Reservation;
import com.upgrade.bean.Timeslot;
import com.upgrade.bean.User;
import com.upgrade.exception.ValidationException;
import com.upgrade.operation.validator.IReservationValidator;
import com.upgrade.operation.validator.impl.ReservationValidator;


@RunWith(SpringJUnit4ClassRunner.class)
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
	
	@Test
	public void createConcurrentReservationTest() throws ValidationException{
		List<ConcurrentReservation> reservations = new ArrayList<>() ; 
		
		User user = new User("A", "B", "C") ;
		Date startDate = new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS)) ; 
		
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		reservations.add(new ConcurrentReservation(reservation)) ;
		reservations.add(new ConcurrentReservation(reservation)) ;
		
		for(ConcurrentReservation res : reservations){
			Thread t = new Thread(res) ;
			t.start();
		}
		
		
		
		
		
	}
	
	private class ConcurrentReservation implements Runnable{
		private Reservation reservation ; 
		public ConcurrentReservation(Reservation reservation){
			this.reservation = reservation ; 
		}
		public void run() {
			IReservationValidator rValidator = new ReservationValidator() ; 
			CreateReservationFactory crFactory = new CreateReservationFactory(rValidator) ;
			try {
				crFactory.createReservation(reservation) ;
			} catch (ValidationException e) {
				e.printStackTrace();
			} 
		}
		
	}
}
