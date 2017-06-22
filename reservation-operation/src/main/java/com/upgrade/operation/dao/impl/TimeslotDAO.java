package com.upgrade.operation.dao.impl;

import java.sql.Date;
import java.util.List;

import com.upgrade.bean.Timeslot;
import com.upgrade.operation.dao.ITimeslotDAO;
import com.upgrade.util.CustomHibernateDaoSupport;

public class TimeslotDAO extends CustomHibernateDaoSupport implements ITimeslotDAO {
	public List<Timeslot> findByRange(Date startTime, Date endTime) {
		@SuppressWarnings("unchecked")
		List<Timeslot> list = (List<Timeslot>)getHibernateTemplate().find(
                "select t from timeslot where (startDate between :startTime and :endTime)"
                + "or (endTime between :startTime and :endTime) " , new Object []{startTime, endTime}
           );
		
		return list ; 
		
	}

}
