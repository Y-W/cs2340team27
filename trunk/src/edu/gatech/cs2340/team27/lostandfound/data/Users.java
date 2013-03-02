package edu.gatech.cs2340.team27.lostandfound.data;

import java.io.IOException;
import java.util.ArrayList;

import edu.gatech.cs2340.team27.lostandfound.model.Communication;


/**
 * This class represents the local user database within the application. 
 * @author Yiqi Chen
 *
 */
public class Users {
	
	private User currentUser;
	private ArrayList<User> list;
	private static Users onlyInstance;
	
	public Users() throws IOException, ClassNotFoundException {
		list = Communication.getInstance().getUserList();
		currentUser = Communication.getInstance().getCurrentUser();
	}
	
	public static Users getInstance() throws IOException, ClassNotFoundException {
		if(onlyInstance == null) {
			onlyInstance = new Users();
		}
		return onlyInstance;
	}
	
/*	public void initialize(ArrayList<User> users, User current) {
		list = users;
		currentUser = current;
	}
*/	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void destruct() {
		onlyInstance = null;
	}
}