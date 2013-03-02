package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.ArrayList;
import java.util.Date;
import edu.gatech.cs2340.team27.lostandfound.data.Item.ItemStatus;
import edu.gatech.cs2340.team27.lostandfound.model.Communication;

public class Items {
	
	private ArrayList<Item> list;
	private static Items onlyInstance;
	
	public static Items getInstance() {
		if(onlyInstance == null) {
			onlyInstance = new Items();
		}
		return onlyInstance;
	}
	
	public void initialize(ArrayList<Item> items) {
		list = items;
	}
	
	public void addItem(ItemStatus status, String name, String location, String description, Date date, User user) {
		Communication.getInstance().addItem(new Item(status, name, location, description, date, user));
		update();
	}
	
	public void update() {
		list = Communication.getInstance().getItems();
	}
	
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
