package com.upgrade.operation.dao;

import org.springframework.transaction.annotation.Transactional;

import com.upgrade.bean.Reservation;

public interface IReservationDAO {
	@Transactional
	public void save(Reservation reservation);
	@Transactional
	public void update(Reservation reservation);
	@Transactional
	public void delete(Reservation reservation);
	@Transactional
	public Reservation findByID(long reservationID);
}
