package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.ArrayList;
import java.util.Date;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

/**
 * Processes everything about items.
 * 
 * @author Yiqi Chen
 *
 */
public class Items {
	
	private ArrayList<Item> list;
	private static Items onlyInstance;
	/**
	 * 
	 * @return the only instance of this class
	 */
	public static Items getInstance() {
		if(onlyInstance == null) {
			onlyInstance = new Items();
		}
		return onlyInstance;
	}
	/**
	 * initialize the list of items
	 * @param items the list items used.
	 */
	public void initialize(ArrayList<Item> items) {
		list = items;
	}
	/**
	 * Adds an item
	 * @param status status of the item
	 * @param name name of the item
	 * @param location location of the item
	 * @param description description of the item
	 * @param date date of losing
	 * @param user the user who creates the item
	 */
	public void addItem(ItemStatus status, String name, String location, String description, Date date, User user) {
		Communication.getInstance().addItem(new Item(status, name, location, description, date, user));
		update();
	}
	/**
	 * update items
	 */
	public void update() {
		list = Communication.getInstance().getItems();
	}
	/**
	 * 
	 * @return the list of lost items
	 */
	public ArrayList<Item> getLost() {
		ArrayList<Item> lost = new ArrayList<Item>();
		for(Item item : list) {
			if(item.getStatus() == ItemStatus.LOST) {
				lost.add(item);
			}
		}
		return lost;
	}
}