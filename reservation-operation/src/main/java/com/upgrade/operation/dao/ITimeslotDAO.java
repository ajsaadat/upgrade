package com.upgrade.operation.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.upgrade.bean.Timeslot;

public interface ITimeslotDAO {
	@Transactional
	public List<Timeslot> findByRange(Date startTime, Date endTime) ; 
}
