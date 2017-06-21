package com.upgrade.operation.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upgrade.bean.Reservation;
import com.upgrade.operation.bo.ReservationBO;
import com.upgrade.operation.dao.IReservationDAO;

@Service("ReservationBO")
public class ReservationBOImpl implements ReservationBO {
	
	@Autowired
	private IReservationDAO reservatioDAO ; 

	public void save(Reservation reservation) {
		reservatioDAO.save(reservation);

	}

	public void update(Reservation reservation) {
		reservatioDAO.update(reservation);

	}

	public void delete(Reservation reservation) {
		reservatioDAO.delete(reservation);

	}

	public Reservation findByID(String reservatioID) {
		return reservatioDAO.findByID(reservatioID) ;
	}

}
