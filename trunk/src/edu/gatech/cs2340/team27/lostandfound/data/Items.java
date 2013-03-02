package edu.gatech.cs2340.team27.lostandfound.data;

import java.util.ArrayList;

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
}
