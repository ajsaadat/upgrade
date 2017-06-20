package com.upgrade.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQuery(name="allTimeslots", query="")
public class Timeslot extends BaseBean {

	@Id
	@GeneratedValue(generator = "assigned")
	@Column(nullable = false)
	private String id ; 
	private Date startDate ; 
	private Date endDate ;
	
	public Timeslot(Date startDate, Date endDate){
		if(startDate == null){
			throw new IllegalArgumentException("Start date can not be null.") ; 
		}else if(endDate == null){
			throw new IllegalArgumentException("End date can not be null.") ; 
		}
		this.endDate = endDate ; 
		this.startDate = startDate ; 
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the duration
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the duration to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Timeslot [id=" + id + ", startDate=" + startDate + ", duration=" + endDate + "]";
	} 
	
	
}
