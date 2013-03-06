package edu.gatech.cs2340.team27.lostandfound.data;

/**
 * This class represents a user and stores all its information. 
 * @author Yiqi Chen
 *
 */
public class User {
	
	protected String name;
	protected String phoneNumber;
	protected String email;
	protected boolean isLocked;
	
	/**
	 * Constructor. 
	 * @param name
	 * @param phoneNumber
	 * @param email
	 */
	public User(String name, String phoneNumber, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		isLocked = false;
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
	
	public boolean isLocked(){
		return isLocked;
	}
	
	public void lock() {
		
	}
	
	public void unlock() {
		
	}
}
