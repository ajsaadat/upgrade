package com.upgrade.operation.dao;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.upgrade.bean.Reservation;

public interface IReservationDAO {
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void save(Reservation reservation);
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void update(Reservation reservation);
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void delete(Reservation reservation);
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public Reservation findByID(long reservationID);
}
