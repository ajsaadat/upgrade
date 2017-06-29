package com.upgrade.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
@Table(name="user")
@Entity
public class User  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable = false)
	private long id ; 
	@Version
	protected int version ;
	private String firstName ; 
	private String lastName ; 
	private String email ; 
	
	public User(){
		
	}
	
	public User(String firstName, String lastName, String email){
		if(firstName == null || firstName.isEmpty()){
			throw new IllegalArgumentException("First name can not be null or empty.") ; 
		}else if(lastName == null || lastName.isEmpty()){
			throw new IllegalArgumentException("last name can not be null or empty.") ; 
		}else if(email == null || email.isEmpty()){
			throw new IllegalArgumentException("Email can not be null or empty.") ; 
		}
		this.email = email ; 
		this.firstName = firstName ;
		this.lastName = lastName ; 
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	} 
	
	
}
