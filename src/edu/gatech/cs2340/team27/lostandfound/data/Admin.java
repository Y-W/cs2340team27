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
	 * @param name
	 * @param phoneNumber
	 * @param email
	 * @param isLocked
	 */
	public Admin(String name, String phoneNumber, String email, boolean isLocked) {
		super(name, phoneNumber, email, isLocked);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor with generated user
	 * @param user
	 */
	public Admin(User user){
		super(user.getName(), user.getPhoneNumber(), user.getEmail(), user.isLocked());
	}
	
	/**
	 * Delete a user
	 * @param email
	 * @return whether the user to be deleted is itself
	 * @throws IOException
	 * @throws ClassNotFoundException
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
	 * @param email
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void unlockUser(String email) throws IOException, ClassNotFoundException {
		Communication.getInstance().unlockUser(email);
	}
	
	/**
	 * Add an administrator account
	 * @param email
	 * @param password
	 * @param realname
	 * @param phone
	 * @return whether the email account has already existed
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public boolean addAdmin(String email, String password, String realname, String phone) throws IOException, ClassNotFoundException {
		return Communication.getInstance().createAccount(email, password, realname, phone, true);
	}
}
