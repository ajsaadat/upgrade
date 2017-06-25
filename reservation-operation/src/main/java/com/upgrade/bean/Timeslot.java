package com.upgrade.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;
@Entity
@Table(name="timeslot", 
indexes = { @Index(name = "timeslotIndex", columnList = "startDate,endDate,version") })
public class Timeslot  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id ;
	private Date startDate ; 
	private Date endDate ;
	@Version
	protected int version ;
	
	public Timeslot(){
		
	}
	
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
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
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
	@Column(unique=true)
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
	@Column(unique=true)
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return "Timeslot [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", version=" + version
				+ "]";
	} 
	
	
}
