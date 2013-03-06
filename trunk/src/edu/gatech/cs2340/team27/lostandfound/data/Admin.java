package edu.gatech.cs2340.team27.lostandfound.data;

import java.io.IOException;

import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class Admin extends User {

	public Admin(String name, String phoneNumber, String email, boolean isLocked) {
		super(name, phoneNumber, email, isLocked);
		// TODO Auto-generated constructor stub
	}
	
	public Admin(User user){
		super(user.getName(), user.getPhoneNumber(), user.getEmail(), user.isLocked());
	}
	
	public boolean deleteUser(String email) throws IOException, ClassNotFoundException {
		if(Users.getInstance().getCurrentUser().getEmail().equals(email)) {
			return false;
		}
		else {
			Communication.getInstance().removeUser(email);
			return true;
		}
	}
	
	public void unlockUser(String email) throws IOException, ClassNotFoundException {
		Communication.getInstance().unlockUser(email);
	}
	
	public void addAdmin(String email, String password, String realname, String phone) throws IOException, ClassNotFoundException {
		Communication.getInstance().createAccount(email, password, realname, phone, true);
	}
}
