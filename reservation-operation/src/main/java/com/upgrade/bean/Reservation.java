package com.upgrade.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
@Table(name="reservation")

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id ; 
	@Version
	protected int version ;
	@OneToOne(cascade = {CascadeType.ALL})
	private User user ; 
	@OneToOne(cascade = {CascadeType.ALL})
	private Timeslot timeslot; 
	private Status status ;
	
	public Reservation(User user, Timeslot timeslot){
		this.timeslot = timeslot ; 
		this.user = user ;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the timeslot
	 */
	public Timeslot getTimeslot() {
		return timeslot;
	}
	/**
	 * @param timeslot the timeslot to set
	 */
	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", version=" + version + ", user=" + user + ", timeslot=" + timeslot
				+ ", status=" + status + "]";
	} 
	
	
	
}
