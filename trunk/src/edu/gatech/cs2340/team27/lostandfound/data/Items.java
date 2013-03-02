package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.ArrayList;
import java.util.Date;

import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;

public class Items {
	
	private ArrayList<Item> list;
	private static Items onlyInstance;
	
	public Items getInstance() {
		if(onlyInstance == null) {
			onlyInstance = new Items();
		}
		return onlyInstance;
	}
	
	public void initialize(ArrayList<Item> items) {
		list = items;
	}
	
	public void addItem(ItemStatus status, String name, String location, String description, Date date, User user) {
		list.add(new Item(status, name, location, description, date, user));
	}
}
