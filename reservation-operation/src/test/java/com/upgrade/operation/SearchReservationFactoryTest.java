package com.upgrade.operation;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
@ContextConfiguration("classpath:config/BeanLocations.xml")
public class SearchReservationFactoryTest {

	
	@Test
	public void searchReservationTest(){
		SearchReservationFactory sFactory = new SearchReservationFactory() ; 
		Date startDate = new Date(System.currentTimeMillis()) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(15, TimeUnit.DAYS)) ; 
		
		sFactory.getPossibleReservationDates(startDate, endDate) ; 
	}
}
