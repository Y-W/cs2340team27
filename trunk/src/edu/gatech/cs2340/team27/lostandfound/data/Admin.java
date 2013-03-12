package edu.gatech.cs2340.team27.lostandfound.data;

import java.io.IOException;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

/**
 * This class represents an administrator. 
 * @author Yiqi Chen
 */
public class Admin extends User {

	/**
	 * Constructor with detailed information
	 * @param name the name of the admin
	 * @param phoneNumber the phone # of the admin
	 * @param email the email of the admin
	 * @param isLocked whether the admin is locked
	 */
	public Admin(String name, String phoneNumber, String email, boolean isLocked) {
		super(name, phoneNumber, email, isLocked);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor with generated user
	 * @param user The user to be generated to admin
	 */
	public Admin(User user){
		super(user.getName(), user.getPhoneNumber(), user.getEmail(), user.isLocked());
	}
	
	/**
	 * Delete a user
	 * @param email The email of the user
	 * @return whether the user to be deleted is itself
	 * @throws IOException throw when file is not found
	 * @throws ClassNotFoundException throw when class is not found
	 */
	public boolean deleteUser(String email) throws IOException, ClassNotFoundException {
		if(Users.getInstance().getCurrentUser().getEmail().equals(email)) {
			return false;
		}
		else {
			Communication.getInstance().removeUser(email);
			return true;
		}
	}
	
	/**
	 * Unlock a user
	 * @param email The email of the user
	 * @throws IOException throw when file is not found
	 * @throws ClassNotFoundException throw when class is not found
	 */
	public void unlockUser(String email) throws IOException, ClassNotFoundException {
		Communication.getInstance().unlockUser(email);
	}
	
	/**
	 * Add an administrator account
	 * @param email the email of the new admin
	 * @param password the password of the new admin
	 * @param realname the realname of the new admin
	 * @param phone the phone number of the new admin
	 * @return whether the email account has already existed
	 * @throws IOException throw when file is not found
	 * @throws ClassNotFoundException throw when class is not found
	 */
	public boolean addAdmin(String email, String password, String realname, String phone) throws IOException, ClassNotFoundException {
		return Communication.getInstance().createAccount(email, password, realname, phone, true);
	}
}
