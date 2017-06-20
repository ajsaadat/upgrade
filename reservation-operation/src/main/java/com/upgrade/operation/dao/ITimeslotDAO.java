package com.upgrade.operation.dao;

import java.sql.Date;
import java.util.List;

import com.upgrade.bean.Timeslot;

public interface ITimeslotDAO {
	
	public List<Timeslot> findByRange(Date startTime, Date endTime) ; 
}
