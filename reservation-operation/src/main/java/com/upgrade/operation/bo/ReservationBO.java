package com.upgrade.operation.bo;

import com.upgrade.bean.Reservation;

public interface ReservationBO {
	public void save(Reservation reservation);
	public void update(Reservation reservation);
	public void delete(Reservation reservation);
	public Reservation findByID(long reservatioID);
}
