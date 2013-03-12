package edu.gatech.cs2340.team27.lostandfound.data;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 * Constructor
	 * @throws ClassNotFoundException won't throw this exception
	 * @throws IOException when IO fails
	 * @throws ParseException when parsing input fails
	 */
	public Items() throws IOException, ClassNotFoundException, ParseException {
		list = Communication.getInstance().getItems();
	}
	/**
	 * This method is used as the singleton design pattern
	 * @return the only instance of this class
	 * @throws ClassNotFoundException won't throw this exception
	 * @throws IOException when IO fails
	 * @throws ParseException when parsing input fails
	 */
	public static Items getInstance() throws IOException, ClassNotFoundException, ParseException {
		if(onlyInstance == null) {
			onlyInstance = new Items();
		}
		return onlyInstance;
	}
	/**
	 * initialize the list of items
	 * @param items the list items used.
	 */
	/*	public void initialize(ArrayList<Item> items) {
		list = items;
	}
*/
	/**
	 * Adds an item
	 * @param status status of the item
	 * @param name name of the item
	 * @param location location of the item
	 * @param description description of the item
	 * @param date date of losing
	 * @param user the user who creates the item
	 * @param category the category of the item 
	 * @throws ClassNotFoundException won't throw this exception
	 * @throws IOException when IO fails
	 * @throws ParseException when parsing input fails
	 */
	public void addItem(ItemStatus status, String name, String location, String description, Date date, User user, Item.Category category) throws IOException, ClassNotFoundException, ParseException {
		Communication.getInstance().addItem(new Item(status, name, location, description, date, user, category));
		update();
	}
	/**
	 * update items
	 * @throws ClassNotFoundException won't throw this exception
	 * @throws IOException when IO fails
	 * @throws ParseException when parsing input fails
	 */
	public void update() throws IOException, ClassNotFoundException, ParseException {
		list = Communication.getInstance().getItems();
	}
	/**
	 * 
	 * @return the list of lost items
	 */
	public ArrayList<Item> getLost() {
		ArrayList<Item> lost = new ArrayList<Item>();
		if (list==null) return lost;
		for(Item item : list) {
			if (item==null) continue;
			if(item.getStatus() == ItemStatus.LOST) {
				lost.add(item);
			}
		}
		return lost;
	}
	
	/**
	 * Filters out the desired item.
	 * @param category the desired category, null if no limitation
	 * @param date the desired date, null if no limitation
	 * @param status the desired status, null if no limitation
	 * @return
	 */
	public List<Item> filter(Item.Category category, Date date, ItemStatus status){
		return null;
	}
}
