package edu.gatech.cs2340.team27.lostandfound.model;

/**
 * This class represents the local user database within the application. 
 * @author Yiqi Chen
 *
 */
public class Users {
	
	private User currentUser;
	private User[] list;
	private static Users onlyInstance;
	
	public static Users getInstance() {
		if(onlyInstance == null) {
			onlyInstance = new Users();
		}
		return onlyInstance;
	}
	
	public void initialize(User[] users, User current) {
		list = users;
		currentUser = current;
	}
	
	public void destruct() {
		onlyInstance = null;
	}
}
