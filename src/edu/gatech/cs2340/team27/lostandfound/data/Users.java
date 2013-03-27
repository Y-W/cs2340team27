package edu.gatech.cs2340.team27.lostandfound.data;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
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
	 * @throws IOException when IO fails
	 * @throws ClassNotFoundException won't throw this exception
	 */
	public Users() throws IOException, ClassNotFoundException {
		list = Communication.getInstance().getUserList();
		currentUser = Communication.getInstance().getCurrentUser();
		if(Communication.getInstance().checkPrivilege(currentUser.email)){
			currentUser=new Admin(currentUser);
		}
	}
	
	/**
	 * 
	 * @return true if current account is admin
	 */
	public boolean isPriviliged(){
		return currentUser instanceof Admin;
	}
	
	/**
	 * This method is used as the singleton design pattern
	 * @return the only instance of this class
	 * @throws IOException when IO fails
	 * @throws ClassNotFoundException won't throw this exception
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
	 * Getter
	 * @return the newest list of users
	 * @throws ClassNotFoundException won't throw this exception
	 * @throws IOException when IO fails
	 */
	public List<User> getListofUsers() throws IOException, ClassNotFoundException{
		return (list = Communication.getInstance().getUserList());
	}
	
	/**
	 * Update current user
	 * @throws IOException when IO fails
	 * @throws ClassNotFoundException won't throw this exception
	 */
	public void updateCurrentUser() throws IOException, ClassNotFoundException {
		currentUser = Communication.getInstance().getCurrentUser();
		if(Communication.getInstance().checkPrivilege(currentUser.email)){
			currentUser=new Admin(currentUser);
		}
	}
	
	/**
	 * Find potential match for a user
	 * @return The list of potential match
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 */
	public List<Item> getPotentialMatch() throws IOException, ClassNotFoundException, ParseException {
		List<Item> match = new LinkedList<Item>();
		if(currentUser == null) return match;
		List<Item> lost = Items.getInstance().getLost(currentUser);
		List<Item> found = Items.getInstance().filter(null, null, ItemStatus.FOUND, null);
		for(Item lostItem : lost) {
			for(Item foundItem : found) {
				if(lostItem.equals(foundItem)) {
					match.add(lostItem);
					match.add(foundItem);
				}
			}
		}
		return match;
	}
	
	/**
	 * Destruct this users instance
	 */
	public void destruct() {
		onlyInstance = null;
	}
}
