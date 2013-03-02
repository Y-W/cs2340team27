package edu.gatech.cs2340.team27.lostandfound.model;

/**
 * This class represents a user and stores all its information. 
 * @author Yiqi Chen
 *
 */
public class User {
	
	protected String name;
	protected String address;
	protected String phoneNumber;
	protected String email;
	
	public User(String name, String address, String phoneNumber, String email) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	/**
	 * Getter
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Getter
	 * @return 
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Getter
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
}
