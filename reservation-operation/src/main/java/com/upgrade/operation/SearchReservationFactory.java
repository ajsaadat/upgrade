package com.upgrade.operation;

import java.sql.Date;
import java.util.List;

import com.upgrade.bean.Timeslot;
import com.upgrade.util.DateUtils;

public class SearchReservationFactory extends ReservationFactory {
	
	public List<Date> getPossibleReservationDates(Date startDate, Date endDate){
		
		List<Timeslot> timeslots = timeslotBO.findByRange(startDate, endDate) ;
		if(timeslots == null || timeslots.isEmpty()){
			return DateUtils.getDaysBetweenDates(startDate, endDate)  ;
		}else{
			List<Date> allDates = DateUtils.getDaysBetweenDates(startDate, endDate) ; 
			for(Timeslot timeslot : timeslots){
				List<Date> bookedDates = DateUtils.getDaysBetweenDates(timeslot.getStartDate(), timeslot.getEndDate()) ;
				allDates.removeAll(bookedDates) ; 
			}
			return allDates ; 
		}
	}
	
}
