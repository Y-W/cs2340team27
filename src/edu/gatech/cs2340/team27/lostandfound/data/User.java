package edu.gatech.cs2340.team27.lostandfound.data;

/**
 * This class represents a user and stores all its information.
 * 
 * @author Yiqi Chen
 * 
 */
public class User {

	private String name;
	private String phoneNumber;
	private String email;
	private boolean isLocked;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            the name of the user
	 * @param phoneNumber
	 *            the phone # of the user
	 * @param email
	 *            the email of the user
	 * @param isLocked
	 *            true if the user is locked
	 */
	public User(String name, String phoneNumber, String email, boolean isLocked) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isLocked = isLocked;
	}

	/**
	 * Getter
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter
	 * 
	 * @return phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Getter
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Override equals method
	 * 
	 * @param user
	 *            The user to compare
	 * @return true if the email is the same
	 */
	public boolean equals(User user) {
		return email.equals(user.email);
	}

	/**
	 * Getter
	 * 
	 * @return If it is locked
	 */
	public boolean isLocked() {
		return isLocked;
	}
}
