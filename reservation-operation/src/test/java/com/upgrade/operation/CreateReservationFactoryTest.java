package com.upgrade.operation;

import java.sql.Date;
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
		Timeslot timeslot = new Timeslot(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))) ;
		Reservation reservation = new Reservation(user, timeslot) ;
		crFactory.createReservation(reservation) ; 
	}
}
