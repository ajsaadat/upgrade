package com.upgrade.operation.cache;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import com.upgrade.bean.Timeslot;
import com.upgrade.util.DateUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class TimeslotCache {

	private static volatile TimeslotCache timeslotCache = null;
	private static Cache ehcache = null; 
	private static ReentrantLock  lock = null ;

	private TimeslotCache(){
		CacheManager.getInstance().addCache("timeslotCache");
		ehcache = CacheManager.getInstance().getCache("timeslotCache") ;
		lock = new ReentrantLock();
	}

	public static TimeslotCache getInstance(){
		if(timeslotCache == null){
			synchronized(TimeslotCache.class){
				if(timeslotCache == null){
					timeslotCache = new TimeslotCache() ;
					return timeslotCache ; 
				}else{
					return timeslotCache ; 
				}
			}
		}else{
			return timeslotCache ; 
		}
	}

	public enum Status{
		WAIT, CONTINUE, DISCONTINUE
	}
	public enum TimeslotStatus{
		RESERVED, UNRESERVED, INPROGRESS
	}
	

	public Status store(Timeslot timeslot){
		lock.lock() ;
		List<Date> reservedDates = DateUtils.getDaysBetweenDates(timeslot.getStartDate(), timeslot.getEndDate()) ;
		List<Element> elements = new ArrayList<Element>() ; 

		try{


			Set<Status> status = new HashSet<Status>(); 
			for(Date date : reservedDates){
				String toStringDate = date.toString() ;
				boolean exist = ehcache.isKeyInCache(toStringDate) ; 
				if(exist){
					Element cacheElement = ehcache.get(toStringDate) ;
					if(cacheElement != null){
						if(cacheElement.getObjectValue().equals(TimeslotStatus.RESERVED)){
							status.add(Status.DISCONTINUE) ;
						}else if(cacheElement.getObjectValue().equals(TimeslotStatus.INPROGRESS)){
							status.add(Status.WAIT) ;
						}else{
							status.add(Status.CONTINUE) ;
						}
					}
				}else{
					Element element = new Element(toStringDate, TimeslotStatus.INPROGRESS) ; 
					elements.add(element) ;
				}
			}

			if(status.contains(Status.DISCONTINUE)){
				return Status.DISCONTINUE ;
			}else if(status.contains(Status.WAIT)){
				return Status.WAIT ; 
			}else{
				ehcache.putAll(elements);
				return Status.CONTINUE ; 
			}
		}finally{
			lock.unlock();
		}

	}
	 

	public void updateTimeslot(Timeslot timeslot, TimeslotStatus status){
		List<Date> reservedDates = DateUtils.getDaysBetweenDates(timeslot.getStartDate(), timeslot.getEndDate()) ;

		lock.lock() ;

		for(Date date : reservedDates){
			Element cacheElement = ehcache.get(date) ;
			if(cacheElement != null){
				switch (status){
				case RESERVED:{
					Element element = new Element(date, TimeslotStatus.RESERVED) ; 
					ehcache.put(element) ;
					break ; 
				}case UNRESERVED:{
					ehcache.remove(date) ;
					break ; 

				}case INPROGRESS:{
					break ; 
				}
				}

			}
		}
	}
}
