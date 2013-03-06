package edu.gatech.cs2340.team27.lostandfound.data;

import java.io.IOException;

import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class Admin extends User {

	public Admin(String name, String phoneNumber, String email) {
		super(name, phoneNumber, email);
		// TODO Auto-generated constructor stub
	}
	
	public Admin(User user){
		super(user.getName(), user.getPhoneNumber(), user.getEmail());
	}
	
	public void deleteUser(String email) {
		
	}
	
	public void unlockUser(String email) {
		
	}
	
	public void addAdmin(String email, String password, String realname, String phone) throws IOException, ClassNotFoundException {
		Communication.getInstance().createAccount(email, password, realname, phone, true);
	}
}
