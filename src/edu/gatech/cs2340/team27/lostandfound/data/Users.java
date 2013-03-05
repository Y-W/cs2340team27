package edu.gatech.cs2340.team27.lostandfound.data;

import java.io.IOException;
import java.util.ArrayList;

import edu.gatech.cs2340.team27.lostandfound.model.Communication;


/**
 * This class represents the local user database within the application. 
 * @author Yiqi Chen
 */
public class Users {
	
	private User currentUser;
	private ArrayList<User> list;
	private static Users onlyInstance;
	
	/**
	 * Constructor
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Users() throws IOException, ClassNotFoundException {
		list = Communication.getInstance().getUserList();
		currentUser = Communication.getInstance().getCurrentUser();
	}
	
	/**
	 * This method is used as the singleton design pattern
	 * @return the only instance of this class
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
	/**
	 * Getter
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Update current user
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void updateCurrentUser() throws IOException, ClassNotFoundException {
		currentUser = Communication.getInstance().getCurrentUser();
	}
	
	/**
	 * Destruct this users instance
	 */
	public void destruct() {
		onlyInstance = null;
	}
}
