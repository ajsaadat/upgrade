package com.upgrade.operation.bo;

import java.sql.Date;
import java.util.List;

import com.upgrade.bean.Timeslot;

public interface TimeslotBO {
	public List<Timeslot> findByRange(Date startTime, Date endTime) ; 
}
