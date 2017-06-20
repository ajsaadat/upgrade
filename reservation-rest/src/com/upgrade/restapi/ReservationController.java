package com.upgrade.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

	@RequestMapping(path="/reservation", method= RequestMethod.POST)
	public long createReservation(){
		return 0; 
	}
	
	@RequestMapping(path="/reservation", method= RequestMethod.PUT)
	public long updateReservation(){
		return 0;
	}
	
	@RequestMapping(path="/reservation", method= RequestMethod.DELETE)
	public boolean cancelReservation(){
		return false ;
	}
	
	
}
