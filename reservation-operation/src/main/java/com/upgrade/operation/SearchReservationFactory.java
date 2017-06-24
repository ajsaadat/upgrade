package com.upgrade.operation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.upgrade.bean.Timeslot;

public class SearchReservationFactory extends ReservationFactory {
	
	public List<Date> getPossibleReservationDates(Date startDate, Date endDate){
		
		List<Timeslot> timeslots = timeslotBO.findByRange(startDate, endDate) ;
		if(timeslots == null || timeslots.isEmpty()){
			return getDaysBetweenDates(startDate, endDate)  ;
		}else{
			List<Date> allDates = getDaysBetweenDates(startDate, endDate) ; 
			for(Timeslot timeslot : timeslots){
				List<Date> bookedDates = getDaysBetweenDates(timeslot.getStartDate(), timeslot.getEndDate()) ;
				allDates.removeAll(bookedDates) ; 
			}
			return allDates ; 
		}
	}
	
	private List<Date> getDaysBetweenDates(Date startDate, Date endDate)
	{
		endDate = new Date(endDate.getTime() + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startDate);

	    while (calendar.getTime().before(endDate))
	    {
	    	
	        Date result = new Date(calendar.getTime().getTime());
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
}
