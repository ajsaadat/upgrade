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
                "select t from Timeslot t where (t.startDate between ? and ?)"
                + "or (t.endDate between ? and ?) " , new Object []{startTime, endTime, startTime, endTime}
           );
		
		return list ; 
		
	}

}
