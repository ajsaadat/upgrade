package com.upgrade.operation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.upgrade.bean.Reservation;
import com.upgrade.operation.dao.IReservationDAO;
import com.upgrade.util.CustomHibernateDaoSupport;

@Repository("ReservationDAO")
public class ReservationDAO extends CustomHibernateDaoSupport implements IReservationDAO {

	public void save(Reservation reservation) {
		getHibernateTemplate().save(reservation) ;

	}

	public void update(Reservation reservation) {
		getHibernateTemplate().update(reservation) ;

	}

	public void delete(Reservation reservation) {
		getHibernateTemplate().delete(reservation) ;

	}

	@SuppressWarnings("rawtypes")
	public Reservation findByID(String reservationID) {
		List list = getHibernateTemplate().find(
                "from reservation where id=?" , reservationID
           );
	return (Reservation)list.get(0);

	}

}
