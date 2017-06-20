package com.upgrade.operation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.upgrade.operation.bo.ReservationBO;
import com.upgrade.operation.bo.TimeslotBO;

public abstract class ReservationFactory {

	private ApplicationContext appContext ;
	protected ReservationBO reservationBO ;
	protected TimeslotBO timeslotBO ; 
	
	public ReservationFactory(){
		appContext =
		    	  new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
		reservationBO = getReservationDAO() ; 
		timeslotBO = getTimeslotBO() ;
	}
	
	private ReservationBO getReservationDAO(){
		return (ReservationBO)appContext.getBean("ReserverationBO") ;
	}
	
	private TimeslotBO getTimeslotBO(){
		return (TimeslotBO)appContext.getBean("TimeslotBO") ; 
	}
}
