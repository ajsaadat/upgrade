package com.upgrade.operation.dao;

import com.upgrade.bean.Reservation;

public interface IReservationDAO {
	public void save(Reservation reservation);
	public void update(Reservation reservation);
	public void delete(Reservation reservation);
	public Reservation findByID(String reservationID);
}
