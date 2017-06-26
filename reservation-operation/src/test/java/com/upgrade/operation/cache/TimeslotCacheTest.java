package com.upgrade.operation.cache;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.upgrade.bean.Timeslot;
import com.upgrade.operation.cache.TimeslotCache.Status;

public class TimeslotCacheTest {
	
	
	@Test(threadPoolSize = 100, invocationCount = 100)
	public void saveTest(){
		TimeslotCache cache = TimeslotCache.getInstance() ; 
		
		Date startDate = new Date(System.currentTimeMillis()+ TimeUnit.MILLISECONDS.convert(2, TimeUnit.DAYS)) ;
		Date endDate = new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(3, TimeUnit.DAYS)) ; 
		
		Timeslot timeslot = new Timeslot(startDate, endDate) ;
		
		Status initialStatus = cache.store(timeslot);
		System.out.println("Initial Status [" + initialStatus + "].");
		/*Status secondaryStatus = cache.store(timeslot) ;
		System.out.println("Secondary Status [" + secondaryStatus + "].");*/
	}
}
