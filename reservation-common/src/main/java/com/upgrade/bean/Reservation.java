package com.upgrade.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Reservation extends BaseBean{

	@Id
	@GeneratedValue(generator = "assigned")
	@Column(nullable = false)
	private String id ; 
	@OneToOne
	private User user ; 
	@OneToOne
	private Timeslot timeslot; 
	private Status status ;
	
	public Reservation(User user, Timeslot timeslot){
		this.timeslot = timeslot ; 
		this.user = user ;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", user=" + user + ", timeslot=" + timeslot + ", status=" + status + "]";
	} 
	
	
	
}
