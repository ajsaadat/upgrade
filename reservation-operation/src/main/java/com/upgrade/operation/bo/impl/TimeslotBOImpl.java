package com.upgrade.operation.bo.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.upgrade.bean.Timeslot;
import com.upgrade.operation.bo.TimeslotBO;
import com.upgrade.operation.dao.ITimeslotDAO;

public class TimeslotBOImpl implements TimeslotBO {

	@Autowired
	ITimeslotDAO timeslotDAO ; 
	
	public List<Timeslot> findByRange(Date startTime, Date endTime) {
		return timeslotDAO.findByRange(startTime, endTime);
	}

}
